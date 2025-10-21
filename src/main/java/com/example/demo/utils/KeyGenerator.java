package com.example.demo.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        // 生成 HS512 算法所需的 512 位密钥
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        // 转为 Base64 字符串（方便配置）
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("生成的密钥：" + base64Key);
    }
}
