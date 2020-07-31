package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginTokenVo implements Serializable {

    private String token;

    private String name;
}
