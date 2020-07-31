package com.arlley.pack.controller;


import com.arlley.pack.constant.ApiResponse;
import com.arlley.pack.entity.User;
import com.arlley.pack.exception.LoginException;
import com.arlley.pack.service.UserService;
import com.arlley.pack.utils.CommonUtils;
import com.arlley.pack.vo.LoginTokenVo;
import com.arlley.pack.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanggang
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/doLogin")
    public LoginTokenVo doLogin(HttpSession httpSession, @RequestBody LoginVo loginVo) throws LoginException {
        User user = userService.isUser(loginVo);

        if(Objects.isNull(user)){
            throw new LoginException("用户名密码错误");
        }else{
            LoginTokenVo loginTokenVo = new LoginTokenVo();
            loginTokenVo.setName(user.getName());
            loginTokenVo.setToken(CommonUtils.getToken(user));
            return loginTokenVo;
        }
    }

    @PostMapping("/address")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/insert")
    public void insert(@RequestBody User user){
        userService.insert(user);
    }

    @PostMapping("/update")
    public void update(@RequestBody User user){
        userService.update(user);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam("id") int id){
        userService.delete(id);
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") int id){
        return userService.getUserById(id);
    }
}
