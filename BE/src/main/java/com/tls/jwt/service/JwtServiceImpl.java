package com.tls.jwt.service;

import com.tls.exception.UnAuthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service("jwtService")
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = "accessToken";

    // 토큰 발행
    @Override
    public String createToken(String subject, long time) {
        if (time <= 0) {
            throw new RuntimeException("Expiry time must be greater than Zero : [" + time + "] ");
        }
        // 토큰을 서명하기 위해 사용해야할 알고리즘 선택
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
            .setSubject(subject)
            .signWith(signatureAlgorithm, signingKey);
        long nowTime = System.currentTimeMillis();
        builder.setExpiration(new Date(nowTime + time));
        return builder.compact();
    }

    // 토큰 해독
    @Override
    public String getSubject(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Override
    public boolean isUsable(String jwt) {
        try {
            Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
            return true;

        } catch (Exception e) {
            throw new UnAuthorizedException();
        }
    }
}