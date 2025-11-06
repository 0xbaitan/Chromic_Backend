package com.hexbaitan.chromic.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hexbaitan.chromic.config.EnvironmentService;
import com.hexbaitan.chromic.users.CrudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtCodec {

    private final Algorithm encryptionAlgorithm;

    private static final long TOKEN_VALIDITY_PERIOD = 15 * 60 * 1000; // 15mins


    @Autowired
    public JwtCodec(EnvironmentService environmentService) {
        encryptionAlgorithm = Algorithm.HMAC256(environmentService.getJwtSecretKey());
    }


    public String generateToken(UserDetails details) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, details.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Instant currentTime = Instant.now();
        Instant expiryTime = currentTime.plusMillis(TOKEN_VALIDITY_PERIOD);
        return JWT.create().withHeader(claims).withIssuedAt(currentTime).withExpiresAt(expiryTime).withSubject(subject).sign(encryptionAlgorithm);
    }


    public String validateToken(String token) throws JWTVerificationException, Exception {
       DecodedJWT decodedJWT = JWT.require(encryptionAlgorithm).build().verify(token);
       Instant currentInstant = Instant.now();
       boolean hasTokenExpired = decodedJWT.getExpiresAtAsInstant().isBefore(currentInstant);
       if(hasTokenExpired) {
           throw new TokenExpiredException("The provided authentication token has expired.", currentInstant);
       }
        return decodedJWT.getSubject();
    }




}
