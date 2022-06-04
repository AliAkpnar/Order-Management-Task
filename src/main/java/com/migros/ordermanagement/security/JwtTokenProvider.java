package com.migros.ordermanagement.security;

import com.migros.ordermanagement.advice.exception.ManagementAPIException;
import com.migros.ordermanagement.advice.exception.OrderNotFoundException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    // TODO : Fix - create props class - Fix Date to LocalDateTime
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationInMs;

    // generate token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // get username from token

    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // validate JWT token
    // TODO : Fix
    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            throw new ManagementAPIException();
        }
        catch (MalformedJwtException ex2){
            throw new ManagementAPIException();
        }
        catch (ExpiredJwtException ex){
            throw new ManagementAPIException();
        }
        catch (UnsupportedJwtException ex){
            throw new ManagementAPIException();
        }
        catch (IllegalArgumentException ex){
            throw new ManagementAPIException();
        }
    }
}
