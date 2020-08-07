package com.arlley.pack.service;

import com.arlley.pack.entity.CaseLawyer;
import com.arlley.pack.entity.CaseMainTable;
import com.arlley.pack.mapper.CaseMainTableMapper;
import com.arlley.pack.vo.CaseLawyerVo;
import com.arlley.pack.vo.CaseTableVo;
import com.arlley.pack.vo.CaseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CaseMainService {

    @Autowired
    private CaseMainTableMapper caseMainTableMapper;

    @Autowired
    private CodeGenService codeGenService;


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
        caseMainTable.setCustomId(1);

        caseMainTableMapper.insert(caseMainTable);
    }

    public List<CaseVo> search() {
        List<CaseMainTable> caseMainTables = caseMainTableMapper.searchAll();
        return null;

    }
}
