package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;
import com.example.demo.vo.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private UserService userService;
    @Resource
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto) {
        // 1. 查询用户
        User user = userService.findByUsername(dto.getUsername());
        if (user == null) {
            return Result.fail("用户不存在！");
        }

        // 2. 验证密码
        if (!userService.verifyPassword(dto.getPassword(), user.getPassword())) {
            return Result.fail("密码错误！");
        }

        // 3. 生成JWT Token
        String token = jwtUtils.generateToken(user.getUsername());
        return Result.success(token);
    }
}
