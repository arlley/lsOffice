package com.arlley.pack.controller;

import com.arlley.pack.entity.Syscode;
import com.arlley.pack.service.SyscodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/code")
public class SyscodeController {

    @Autowired
    private SyscodeService syscodeService;

    @GetMapping("/getCodeByCodeType")
    public Syscode getSyscodeByTypeAndCode(@RequestParam("codeCode") String codeCode, @RequestParam("codeType") String codeType){
        return syscodeService.getSyscodeByTypeAndCode(codeType, codeCode);
    }

    @GetMapping("/getCodesByType")
    public List<Syscode> getSyscodeByType(@RequestParam("codeType") String codeType){
        return syscodeService.getSyscodeByType(codeType);
    }
}
