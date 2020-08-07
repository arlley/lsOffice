package com.arlley.pack.controller;


import com.arlley.pack.service.LawyerService;
import com.arlley.pack.vo.LawyerSearchVo;
import com.arlley.pack.vo.LawyerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanggang
 * @since 2020-08-03
 */
@RestController
@RequestMapping("/lawyer")
public class LawerController {

    @Autowired
    private LawyerService lawyerService;

    @PostMapping("/insert")
    public void insertLawyer(@RequestBody LawyerVo lawyerVo){
        this.lawyerService.insertLawyer(lawyerVo);
    }

    @PostMapping("/query")
    public List<LawyerVo> search(@RequestBody LawyerSearchVo lawyerSearchVo){
        return lawyerService.query(lawyerSearchVo);
    }
}
