package com.arlley.pack.service;

import com.arlley.pack.entity.CaseLawyer;
import com.arlley.pack.entity.CaseMainTable;
import com.arlley.pack.entity.Custom;
import com.arlley.pack.entity.PayDetail;
import com.arlley.pack.mapper.CaseLawyerMapper;
import com.arlley.pack.mapper.CaseMainTableMapper;
import com.arlley.pack.mapper.CustomMapper;
import com.arlley.pack.mapper.PayDetailMapper;
import com.arlley.pack.vo.CaseLawyerVo;
import com.arlley.pack.vo.CaseSearchVo;
import com.arlley.pack.vo.CaseTableVo;
import com.arlley.pack.vo.CaseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CaseMainService {

    @Autowired
    private CaseMainTableMapper caseMainTableMapper;

    @Autowired
    private CodeGenService codeGenService;

    @Autowired
    private CaseLawyerMapper caseLawyerMapper;

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private PayDetailMapper payDetailMapper;

    public CaseTableVo init(){
        String caseNo = codeGenService.getNoBypNo("C");
        CaseTableVo result = new CaseTableVo();

        String contractNo = codeGenService.getNoBypNo("A");

        String[] caseNos = caseNo.split(",");
        result.setCaseNoFirst(caseNos[0]);
        result.setCaseNoSecond(caseNos[1]);
        String[] contractNos = contractNo.split(",");
        result.setCaseNo(caseNos[0] +"(民)第"+caseNos[1]);
        result.setContractNo(contractNos[0]+"(民)第"+contractNos[1]);
        List<CaseLawyerVo> caseLawyerVos = new ArrayList<>();
        CaseLawyerVo caseLawyerVo = new CaseLawyerVo();
        caseLawyerVo.setJob("1");
        caseLawyerVos.add(caseLawyerVo);
        result.setCaseLawyerVos(caseLawyerVos);
        return result;
    }


    public void insert(CaseTableVo caseTableVo){

        CaseMainTable caseMainTable = new CaseMainTable();
        BeanUtils.copyProperties(caseTableVo, caseMainTable);
        caseMainTable.setCaseState("未结案");
        caseMainTable.setApproveState("未审批");
        caseMainTableMapper.insert(caseMainTable);

        for(CaseLawyerVo caseLawyerVo : caseTableVo.getCaseLawyerVos()){
            CaseLawyer caseLawyer = new CaseLawyer();
            BeanUtils.copyProperties(caseLawyerVo, caseLawyer);
            caseLawyer.setCaseId(caseMainTable.getId());
            caseLawyerMapper.insert(caseLawyer);
        }
    }

    public List<CaseVo> search(CaseSearchVo caseSearchVo) {

        if(StringUtils.isBlank(caseSearchVo.getCustomName())){
            caseSearchVo.setCustomName(null);
        }
        if(StringUtils.isBlank(caseSearchVo.getParty())){
            caseSearchVo.setParty(null);
        }
        List<CaseMainTable> caseMainTables = caseMainTableMapper.searchAll(caseSearchVo);
        List<CaseVo> caseVos = new ArrayList<>();
        for(CaseMainTable caseMainTable : caseMainTables){
            CaseVo caseVo = new CaseVo();
            BeanUtils.copyProperties(caseMainTable, caseVo);
            caseVo.setAlreadyInvoice(BigDecimal.ZERO);
            caseVo.setAlreadyPay(BigDecimal.ZERO);
            // 委托人信息
            Custom custom = customMapper.selectById(caseMainTable.getCustomId());
            caseVo.setCustomName(custom.getName());

            // 律师
            List<String> lawyerName = caseLawyerMapper.selectByCaseId(caseVo.getId());
            caseVo.setLawyer(String.join(",", lawyerName));
            caseVos.add(caseVo);
        }
        return caseVos;
    }

    public CaseMainTable getById(int id) {
        return caseMainTableMapper.selectById(id);
    }

    public void audit(int id, int auditRes, String name) {
        CaseMainTable caseMainTable = caseMainTableMapper.selectById(id);
        if(auditRes == 1) {
            caseMainTable.setApproveState("审核通过");

        }else{
            caseMainTable.setApproveState("审核不通过");
        }
        caseMainTable.setApprover(name);
        caseMainTableMapper.updateById(caseMainTable);
    }

    public void pay(int id, BigDecimal payVResult, int lawyerId) {
        CaseMainTable caseMainTable = caseMainTableMapper.selectById(id);
        if(caseMainTable.getAlreadyPay() == null){
            caseMainTable.setAlreadyPay(BigDecimal.ZERO);
        }
        caseMainTable.setAlreadyPay(caseMainTable.getAlreadyPay().add(payVResult));
        caseMainTableMapper.updateById(caseMainTable);
        PayDetail special = new PayDetail();
        special.setIncomeType(1);
        special.setIsSpecial(1);
        special.setCreateTime(LocalDate.now());
        special.setCaseId(id);
        special.setNote("案件收入");
        special.setPayAmount(BigDecimal.valueOf(0.15).multiply(payVResult));
        special.setPayTime(LocalDate.now());
        special.setPayType("1");
        special.setPayWay("1");
        payDetailMapper.insert(special);
        special.setIsSpecial(0);
        special.setLawyerId(lawyerId);
        special.setPayAmount(BigDecimal.valueOf(0.85).multiply(payVResult));
        special.setId(null);
        payDetailMapper.insert(special);
    }
}
