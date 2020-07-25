package com.arlley.pack.controller;


import com.arlley.pack.exception.BusinessException;
import com.arlley.pack.service.UserService;
import com.arlley.pack.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author wanggang
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/user")
public class QianchengUserTableController {

    @Autowired
    private UserService userService;

    @PostMapping("/doLogin")
    public LoginVo doLogin(@RequestBody LoginVo loginVo) throws BusinessException {
        loginVo = userService.isUserExist(loginVo);

        if(Objects.isNull(loginVo)){
            throw new BusinessException("账号密码不正确");
        }else{
            return loginVo;
        }
    }
}
