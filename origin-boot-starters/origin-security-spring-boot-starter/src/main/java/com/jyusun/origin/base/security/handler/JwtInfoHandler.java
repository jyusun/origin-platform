package com.jyusun.origin.base.security.handler;

import com.jyusun.origin.base.security.config.props.JwtTokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtInfoHandler {

    private final JwtTokenProperties jwtTokenProperties;

    /**
     * 生成token签名
     * @param subject
     * @return
     */
    public String createToken(String subject) {
        Date now = new Date();
        // 过期时间
        Date expireDate = new Date(now.getTime() + jwtTokenProperties.getExpire() * 1000);

        // 创建Signature SecretKey
        final SecretKey key = Keys.hmacShaKeyFor(jwtTokenProperties.getSecret().getBytes(StandardCharsets.UTF_8));

        // header参数
        final Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", SignatureAlgorithm.HS256.getValue());
        headerMap.put("typ", "JWT");

        // 生成token
        String token = Jwts.builder()
            .setHeader(headerMap)
            .setSubject(subject)
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .setIssuer(jwtTokenProperties.getIssuer())
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

        log.info("JWT[" + token + "]");
        return token;
    }

    /**
     * 解析token
     * @param token token
     * @return
     */
    public Claims parseToken(String token) {

        Claims claims = null;
        try {
            // 创建Signature SecretKey
            final SecretKey key = Keys.hmacShaKeyFor(jwtTokenProperties.getSecret().getBytes(StandardCharsets.UTF_8));

            claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            log.info("Parse JWT token success");
        }
        catch (JwtException e) {
            log.info("Parse JWT errror " + e.getMessage());
            return null;
        }
        return claims;
    }

    /**
     * 判断token是否过期
     * @param expiration
     * @return
     */
    public boolean isExpired(Date expiration) {
        return expiration.before(new Date());
    }

}