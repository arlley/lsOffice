package com.arlley.pack.controller;


import com.arlley.pack.entity.PayDetail;
import com.arlley.pack.service.PayDetailService;
import com.arlley.pack.vo.CaseSearchVo;
import com.arlley.pack.vo.PayDetailDetailVo;
import com.arlley.pack.vo.PayDetailSearchVo;
import com.arlley.pack.vo.PayDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanggang
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/pay/detail")
public class PayDetailController {

    @Autowired
    private PayDetailService payDetailService;

    @PostMapping("/search")
    public List<PayDetailDetailVo> search(@RequestBody PayDetailSearchVo searchVo){
        return payDetailService.search(searchVo);
    }

    @GetMapping("/index")
    public PayDetailVo index(){
        return payDetailService.index(true, 0);
    }

    @GetMapping("/person")
    public PayDetailVo person(@RequestParam("id") int id){
        return payDetailService.index(false, id);
    }

    @PostMapping("/my")
    public List<PayDetailDetailVo> my(@RequestBody PayDetailSearchVo searchVo){
        return payDetailService.my(searchVo);
    }


    @PostMapping("/insertLawyer")
    public void insertLawyer(@RequestBody PayDetail payDetail){
        payDetailService.insertPayDetailLawyer(payDetail);
    }
    @PostMapping("/insertMyLawyer")
    public void insertMyLawyer(@RequestBody PayDetail payDetail){
        payDetailService.insertPayDetailMyLawyer(payDetail);
    }

    @PostMapping("/insertLawyerOut")
    public void insertLawyerOut(@RequestBody PayDetail payDetail){
        payDetailService.insertPayDetailLawyerOut(payDetail);
    }

    @PostMapping("/insertLawyerMyOut")
    public void insertLawyerMyOut(@RequestBody PayDetail payDetail){
        payDetailService.insertPayDetailMyLawyerOut(payDetail);
    }
}
