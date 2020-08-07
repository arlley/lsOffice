package com.arlley.pack.controller;


import com.arlley.pack.entity.DbYhmCity;
import com.arlley.pack.service.DbYhmCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanggang
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/city")
public class DbYhmCityController {

    @Autowired
    private DbYhmCityService dbYhmCityService;

    @GetMapping("/classType")
    public List<DbYhmCity> getByClassType(int classType){
        return dbYhmCityService.getByClassType(classType);
    }
}
