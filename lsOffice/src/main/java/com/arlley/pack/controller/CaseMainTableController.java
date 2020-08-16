package com.arlley.pack.controller;


import com.arlley.pack.entity.CaseMainTable;
import com.arlley.pack.service.CaseMainService;
import com.arlley.pack.vo.CaseSearchVo;
import com.arlley.pack.vo.CaseTableVo;
import com.arlley.pack.vo.CaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanggang
 * @since 2020-08-02
 */
@RestController
@RequestMapping("/case/main")
public class CaseMainTableController {

    @Autowired
    private CaseMainService caseMainService;


    @GetMapping("/init")
    public CaseTableVo init(){
        return caseMainService.init();
    }

    @PostMapping("/insert")
    public void insert(@RequestBody CaseTableVo caseTableVo){
        caseMainService.insert(caseTableVo);
    }


    @PostMapping("/search")
    public List<CaseVo> search(@RequestBody CaseSearchVo caseSearchVo){
        return caseMainService.search(caseSearchVo);
    }

    @GetMapping("/detail")
    public CaseMainTable getById(@RequestParam("id") int id){
        return caseMainService.getById(id);
    }

    @GetMapping("/audit")
    public void audit(@RequestParam("id") int id, @RequestParam("auditRes")int auditRes, @RequestParam("name")String name){
        caseMainService.audit(id, auditRes, name);
    }

    @GetMapping("/pay")
    public void pay(@RequestParam("id") int id, @RequestParam("payVResult") BigDecimal payVResult, @RequestParam("lawyerId") int lawyerId){
        caseMainService.pay(id, payVResult, lawyerId);
    }
}
