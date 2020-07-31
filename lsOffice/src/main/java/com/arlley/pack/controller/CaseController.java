package com.arlley.pack.controller;


import com.arlley.pack.entity.Case;
import com.arlley.pack.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanggang
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/case")
public class CaseController {
    @Autowired
    private CaseService caseService;

    @GetMapping("/list")
    public List<Case> getAllCase(){
        return caseService.getAllCase();
    }
    @PostMapping("/insert")
    public void insert(@RequestBody Case cass){
        caseService.insert(cass);
    }

    @PostMapping("/update")
    public void update(@RequestBody Case cass){
        caseService.update(cass);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam("id") int id){
        caseService.delete(id);
    }
}
