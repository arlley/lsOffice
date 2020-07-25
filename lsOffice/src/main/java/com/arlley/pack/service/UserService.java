package com.arlley.pack.service;

import com.arlley.pack.entity.QianchengUserTable;
import com.arlley.pack.mapper.QianchengUserTableMapper;
import com.arlley.pack.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private QianchengUserTableMapper qianchengUserTableMapper;


    public LoginVo isUserExist(LoginVo loginVo){
        QianchengUserTable qianchengUserTable = qianchengUserTableMapper.selectByUserNameAndPassword(loginVo.getUserName(), loginVo.getPassword());
        if(Objects.isNull(qianchengUserTable)) {
            return null;
        }else{
            return loginVo;
        }
    }
}
