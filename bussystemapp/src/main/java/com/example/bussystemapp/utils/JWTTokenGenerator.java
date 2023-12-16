package com.example.bussystemapp.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.jfr.Category;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenGenerator {

    //private key for generating jwt tokens

    private static final String jwtSecret = "exN9IOG6w0Bo8Mg7e6Y700R3Rt6ZvQ2TBocIKNpzLnev80BsA6bGmr0T6cXSFWuqexN9IOG6w0Bo8Mg7e6Y700R3Rt6ZvQ2TBocIKNpzLnev80BsA6bGmr0T6cXSFWuq";


    public String generateToken(Authentication auth){
        UserDetails userDto = (UserDetails) auth.getPrincipal();

        int expirationTime = 86400000;
        return Jwts.builder().setSubject(userDto.getUsername())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + expirationTime))
                .signWith(SignatureAlgorithm.RS512,jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
            return true;
        }catch (Exception exception){
            System.out.println("Invalid jwt token");
        }

        return false;

    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }
}
