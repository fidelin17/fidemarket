package com.msfide.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JWTUtil {
    private static  final String KEY="fide";
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,KEY).compact();
    }

    //validar si el jwt es correcto
    public  boolean validateToken(String token, UserDetails userDetails){
        return  userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpited(token);
    }

    //extraer el usuario del token
    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    //verificar si el token aun no expira
    public boolean isTokenExpited(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
