package com.rig.book.service;

import com.rig.book.entity.UserEntity;
import com.rig.book.exceptions.BadRequestException;
import com.rig.book.model.UserModel;
import com.rig.book.repos.UserRepository;
import org.apache.catalina.users.DataSourceUserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> roles=null;
        UserEntity user = userRepository.findByUsername(username);
        if (Objects.nonNull(user)) {
            roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
            return new User(user.getUsername(), user.getPassword(), roles);
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public void save(UserModel userModel) {
        UserEntity user = userRepository.findByUsername(userModel.getUsername());
        if (Objects.nonNull(user)) {
            throw new BadRequestException("username already exists");
        }
        userRepository.save(UserEntity.builder().username(userModel.getUsername())
                .password(bcryptEncoder.encode(userModel.getPassword()))
                .role(userModel.getRole()).build());
    }
}
