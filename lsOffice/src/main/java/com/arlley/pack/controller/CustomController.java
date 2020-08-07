package com.arlley.pack.controller;


import com.arlley.pack.entity.Custom;
import com.arlley.pack.service.CustomService;
import com.arlley.pack.vo.CustomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    private CustomService customService;

    @GetMapping("/list")
    public List<CustomVo> getCustom(){
        return customService.selectList(null);
    }

    @PostMapping("/search")
    public List<CustomVo> searchCustom(@RequestBody CustomVo customVo){
        return customService.selectList(customVo);
    }

    @PostMapping("/insert")
    public void insertCustom(@RequestBody Custom custom){
        custom.setAssign("2");
        customService.insert(custom);
    }

    @GetMapping("/getById")
    public Custom getCustom(@RequestParam("id") int id){
        return customService.getById(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody Custom custom){
        customService.update(custom);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam("id") int id){
        customService.delete(id);
    }
}
