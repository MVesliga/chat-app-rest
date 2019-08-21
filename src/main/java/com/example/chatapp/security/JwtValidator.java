package com.example.chatapp.security;

import com.example.chatapp.model.JwtUser;
import com.example.chatapp.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "nekisecret";

    public User validate(String token) {

        User user = null;

        try{
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

            user = new User();
            user.setEmail(body.getSubject());
            user.setUsername((String) body.get("username"));
            user.setRole((String) body.get("role"));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }
}
