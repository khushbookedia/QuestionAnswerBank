package com.neosoft.QuestionAnswerBank.Security.jwt;

import com.neosoft.QuestionAnswerBank.Security.services.JwtUserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("secretkeysecretkey")
    private String jwtSecret;

    @Value("86400000")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){

        JwtUserDetailsImpl userPrincipal = (JwtUserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }
        catch (SignatureException e){
            logger.error("Invalid Jwt Signature: {}", e.getMessage());
        }
        catch (MalformedJwtException e){
            logger.error("Invalid Jwt Signature: {}", e.getMessage());
        }
        catch (ExpiredJwtException e){
            logger.error("Jwt token is expired: {}", e.getMessage());
        }
        catch (UnsupportedJwtException e){
            logger.error("Jwt token is unsupported: {}", e.getMessage());
        }
        catch (IllegalArgumentException e){
            logger.error("Jwt claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
