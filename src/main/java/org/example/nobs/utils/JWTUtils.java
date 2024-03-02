package org.example.nobs.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;

public class JWTUtils {
    private static final String SEC_RET = "your-secret-key";
    private static final long EXP_TIME = 864_000_000;
    public static String generateToken (String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() +EXP_TIME)) // 10 days
                .signWith(SignatureAlgorithm.HS512,SEC_RET)
                .compact();
    }
    //later will have another method for extract username
    public static String extractUsername (String token){
        return Jwts.parser().setSigningKey(SEC_RET)
                .parseClaimsJws(token).getBody()
                .getSubject();
    }
    //method for validateToken
    public static boolean validateToken (String token){
        try{
            Jwts.parser().setSigningKey(SEC_RET).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            e.printStackTrace();
        }
        return false;
    }
}
