package com.arlley.pack.service;

import com.arlley.pack.entity.Pay;
import com.arlley.pack.entity.User;
import com.arlley.pack.mapper.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PayService {


    @Autowired
    private PayMapper payMapper;

    public List<Pay> getAllPay(){
        return payMapper.selectAllPay();
    }

    public Pay getPayById(int id){
        return payMapper.selectById(id);
    }

    public void insert(Pay pay){
        pay.setCreateTime(LocalDateTime.now());
        payMapper.insert(pay);
    }

    public void update(Pay pay){
        payMapper.updateById(pay);
    }

    public void delete(int id){
        payMapper.deleteById(id);
    }
}
