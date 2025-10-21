package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;
import com.example.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto) {
        // 实际项目中需校验用户名密码，此处简化
//        if ("admin".equals(dto.getUsername()) && "123456".equals(dto.getPassword())) {
//            String token = "generated-jwt-token"; // 实际用 JWT 工具生成
//            return Result.success(token);
//        }
//        return Result.fail("用户名或密码错误");

        // 1. 查询用户
        User user = userService.getUserByUsername(dto.getUsername());
        if (user == null) {
            return Result.fail("用户不存在！");
        }

        // 2. 验证密码
        if (!userService.checkPassword(dto.getPassword(), user.getPassword())) {
            return Result.fail("密码错误！");
        }

        // 3. 生成JWT Token
        String token = jwtUtils.generateToken(user.getUsername());
        System.out.println(token);
        Result<String> success = Result.success(token);
        return Result.success(token);
    }
}
