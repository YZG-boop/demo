package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto) {
        // 实际项目中需校验用户名密码，此处简化
        if ("admin".equals(dto.getUsername()) && "123456".equals(dto.getPassword())) {
            String token = "generated-jwt-token"; // 实际用 JWT 工具生成
            return Result.success(token);
        }
        return Result.fail("用户名或密码错误");
    }
}
