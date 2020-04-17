package de.tchuensu.home.springbootserver.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tchuensu.home.springbootserver.dao.dto.model.AuthenticationData;
import de.tchuensu.home.springbootserver.services.UserServiceImpl;
import de.tchuensu.home.springbootserver.web.exception.UserCredentialException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            // We get the payload of the request and using an ObjectMapper we serialize
            // this payload to the corresponding java object
            AuthenticationData authenticationData = new ObjectMapper()
                    .readValue(request.getInputStream(), AuthenticationData.class);

            // We use the authenticate method of the AuthenticationManager to  authenticate
            // a user. A UsernamePasswordAuthenticationToken instance initialized with the
            //  user log in infos is issued.
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationData.getUsername(),
                            authenticationData.getPassword(),
                            new ArrayList<>()
                    )
            );

        } catch (IOException e) {
            throw new UserCredentialException(" User Credential Reading Failed !!!");  
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        if( authResult.getPrincipal() != null ) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
            String username = user.getUsername();
            if(username != null && username.length() > 0) {
                String token = Jwts.builder()
                        .setSubject(username)
                        .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                        .compact();
                response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

                // Authorization is not a standard header for http response, with the following command we make sure that
                // will be listed among the headers received from the server
                response.setHeader("Access-Control-Expose-Headers" ,"Authorization");
            }
        }

    }
}
