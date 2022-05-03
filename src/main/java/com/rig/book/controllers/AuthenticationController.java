package com.rig.book.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rig.book.controllers.webModel.AuthenticationRequestModel;
import com.rig.book.controllers.webModel.AuthenticationResponseModel;
import com.rig.book.controllers.webModel.UserRequestModel;
import com.rig.book.model.UserModel;
import com.rig.book.service.UserDetailsServiceImpl;
import com.rig.book.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponseModel> createJwtToken(
            @RequestBody AuthenticationRequestModel authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ValidationException("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseModel(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequestModel user) {
        UserModel userModel = objectMapper.convertValue(user,UserModel.class);
        userModel.setRole("ROLE_USER");
        userDetailsService.save(userModel);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
