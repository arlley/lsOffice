package com.arlley.pack.constant;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private int code;

    private String message;

    private T Data;
}
