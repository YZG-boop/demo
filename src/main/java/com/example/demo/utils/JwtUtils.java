package com.example.demo.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret; // 从配置文件读取密钥

    @Value("${jwt.expiration}")
    private long expiration; // 过期时间（毫秒）

    // 生成Token
    public String generateToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username) // 存储用户名
                .setIssuedAt(now) // 签发时间
                .setExpiration(expirationDate) // 过期时间
                .signWith(SignatureAlgorithm.HS512, secret) // 签名算法+密钥
                .compact();
    }

    // 从Token中获取用户名
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 验证Token有效性
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Token无效或过期
        }
    }
}
