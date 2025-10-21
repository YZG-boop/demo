package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 1. 配置跨域信息
        CorsConfiguration config = new CorsConfiguration();
        // 允许前端的域名（本地开发是 http://localhost:5173）
        config.addAllowedOrigin("http://localhost:5173");
        // 允许携带 Cookie（如果登录需要记住用户状态）
        config.setAllowCredentials(true);
        // 允许所有请求方法（GET/POST/PUT/DELETE 等）
        config.addAllowedMethod("*");
        // 允许所有请求头（如 Content-Type、Authorization）
        config.addAllowedHeader("*");
        // 跨域请求有效期（30分钟，避免频繁预检请求）
        config.setMaxAge(1800L);

        // 2. 配置需要跨域的路径（所有接口都允许）
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // /** 表示所有接口

        // 3. 返回过滤器
        return new CorsFilter(source);
    }
}
