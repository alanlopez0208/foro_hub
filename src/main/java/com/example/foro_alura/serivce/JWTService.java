package com.example.foro_alura.serivce;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.foro_alura.domain.entity.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTService {
    private final Algorithm algorithm = Algorithm.HMAC256("foro-alura");

    public String getToken(Usuario usuario){
        String token = JWT.create()
                .withIssuer("foro-alura")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-05:00")))
                .sign(algorithm);
        return token;
    }

    public String getUserName(String token){
        try{
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("foro-alura")
                    .build();
            return verifier.verify(token).getSubject();
        }catch (JWTVerificationException ex){
            return null;
        }
    }

}
