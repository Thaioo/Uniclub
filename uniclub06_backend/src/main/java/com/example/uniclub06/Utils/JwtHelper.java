package com.example.uniclub06.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {


    @Value("${jwts.key}")
    private String key;

    private int expirationTime= 8*60*60*1000;

    public String  generateToken(String data) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        Date CurrentDate =  new Date();
        long milliseconds = CurrentDate.getTime()+expirationTime;
        Date dateFuture = new Date(milliseconds);
        String token = Jwts.builder().signWith(secretKey).expiration(dateFuture).subject(data).compact();
        return token;
    }
    public String decodeToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
       return  Jwts.parser().verifyWith(secretKey).build()
                .parseClaimsJws(token)
                .getPayload()
                .getSubject();
    }
}
