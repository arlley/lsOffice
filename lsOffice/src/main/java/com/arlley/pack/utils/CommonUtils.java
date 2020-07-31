package com.arlley.pack.utils;

import com.arlley.pack.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public final class CommonUtils {

    private CommonUtils(){}


    public static String getToken(User user){
        String token = JWT.create().withAudience(user.getId()+"").sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
