package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // 实际项目中应注入UserMapper查询数据库
    // 此处简化为模拟数据库查询
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // 根据用户名查询用户（实际应从数据库获取）
    public User getUserByUsername(String username) {
        // 模拟数据库中的用户（密码已加密）
        if ("admin".equals(username)) {
            User user = new User();
            user.setId(1L);
            user.setUsername("admin");
            // 加密存储的密码（对应明文"123456"）
            user.setPassword(passwordEncoder.encode("123456"));
            return user;
        }
        return null;
    }

    // 验证密码
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
