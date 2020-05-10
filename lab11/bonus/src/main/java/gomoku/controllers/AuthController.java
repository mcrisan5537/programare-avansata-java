package gomoku.controllers;

import gomoku.MainApp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

@RestController
public class AuthController {

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody String string) {
        Instant instant = Instant.now();

        String JWTString = Jwts.builder()
                .claim("name", string)
                .setIssuedAt(Date.from(instant))
                .signWith(SignatureAlgorithm.HS512, MainApp.SECRET)
                .compact();

        return "successfully authenticated\n + JWT: " + JWTString;
    }

    @GetMapping("/hello")
    public String sayHello(@RequestHeader("Authorization") String authorization) {
        String JWT = authorization.split(" ")[1];
        Jws<Claims> result = Jwts.parser()
                .setSigningKey(MainApp.SECRET)
                .parseClaimsJws(JWT);
        return "hello " + result.getBody().get("name", String.class);
    }

}
