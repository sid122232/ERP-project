package com.project.erpSystem.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {



    private String SECRET_KEY = "TaK+HaV^uvCHEFSEVfypW#7g9^k*Z8$V";


    private SecretKey getSigningKey(){  // converts in the secret key in hmacShas encryption
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }


    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token){
        return extractAllClaims(token).get("role", String.class
                );
    }



    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration (String token){
        return extractAllClaims(token).getExpiration();
    }


    private Claims extractAllClaims(String token){
        return  Jwts.parser()
                .verifyWith(getSigningKey())// checks signing key is unchanged or with secret key
                .build()// builds
                .parseSignedClaims(token) // decode claims from  token
                .getPayload();// gets the payload
    }


    public String generateToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // Add the role to claims

        return createToken(claims, username);
    }


    private String createToken(Map<String , Object > claims, String subject){
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("typ", "JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSigningKey())
                .compact();

    }


    public boolean validateToken (String token , String userName){
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(userName) && !isTokenExpired(token));

    }
}
