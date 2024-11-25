package com.example.blog._core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resp<T> {
    private Boolean success;
    private String msg;
    private T body;

    public static <T> Resp<T> ok(T body) {
        return new Resp<T>(true, "성공", body);
    }

    public static <T> Resp<T> fail(String msg) {
        return new Resp<T>(false, msg, null);
    }
}
