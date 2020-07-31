package com.arlley.pack.controller;


import com.arlley.pack.entity.Pay;
import com.arlley.pack.entity.User;
import com.arlley.pack.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanggang
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @GetMapping("/pays")
    public List<Pay> getAllPay(){
        return payService.getAllPay();
    }

    @GetMapping("/getPay")
    public Pay getPay(@RequestParam("id") int id){
        return payService.getPayById(id);
    }
    @PostMapping("/insert")
    public void insert(@RequestBody Pay pay){
        payService.insert(pay);
    }

    @PostMapping("/update")
    public void update(@RequestBody Pay pay){
        payService.update(pay);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam("id") int id){
        payService.delete(id);
    }

}
