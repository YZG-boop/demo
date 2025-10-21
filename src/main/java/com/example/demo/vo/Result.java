package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应体
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code; // 200成功，500失败，401未认证
    private String msg;
    private T data;

    // 成功响应
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 失败响应
    public static <T> Result<T> fail(String msg) {
        return new Result<>(500, msg, null);
    }

    // 未认证响应
    public static <T> Result<T> unauth() {
        return new Result<>(401, "请先登录", null);
    }
}
