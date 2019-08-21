package com.example.chatapp.security;

import com.example.chatapp.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


@Component
public class JwtGenerator {

    public String generate(User user) {

        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "nekisecret").compact();
    }
}
