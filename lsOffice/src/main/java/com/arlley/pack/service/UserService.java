package com.arlley.pack.service;

import com.arlley.pack.entity.User;
import com.arlley.pack.mapper.UserMapper;
import com.arlley.pack.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User isUser(LoginVo loginVo){
        User user = userMapper.selectByTelAndPassword(loginVo.getUserName(), loginVo.getPassword());
        return user;
    }

    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

    public void insert(User user){
        userMapper.insert(user);
    }

    public void update(User user){
        userMapper.updateById(user);
    }

    public void delete(int id){
        userMapper.deleteById(id);
    }
}
