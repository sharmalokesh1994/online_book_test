package com.rig.book.exceptionshandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String message = "";
        final Exception exception = (Exception) request.getAttribute("exception");
        if(Objects.nonNull(exception)) {
            if( exception instanceof ExpiredJwtException ) {
                message = "token got expired";
            } else if( exception instanceof BadCredentialsException ) {
                message = "please provide correct username or password";
            }
        } else {
            message = authException.getMessage();
        }

        if(!StringUtils.hasText(message)) {
            message = "Unauthorized value";
        }
        byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
        response.getOutputStream().write(body);
    }
}
