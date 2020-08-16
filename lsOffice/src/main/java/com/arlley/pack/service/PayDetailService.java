package com.arlley.pack.service;

import com.arlley.pack.entity.PayDetail;
import com.arlley.pack.entity.Syscode;
import com.arlley.pack.mapper.PayDetailMapper;
import com.arlley.pack.mapper.SyscodeMapper;
import com.arlley.pack.vo.PayDetailDetailVo;
import com.arlley.pack.vo.PayDetailSearchVo;
import com.arlley.pack.vo.PayDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PayDetailService {


    @Autowired
    private PayDetailMapper payDetailMapper;

    @Autowired
    private SyscodeMapper syscodeMapper;

    public List<PayDetailDetailVo> search(PayDetailSearchVo searchVo){
        if(StringUtils.isBlank(searchVo.getPayType())){
            searchVo.setPayType(null);
        }
        if(StringUtils.isBlank(searchVo.getPayWay())){
            searchVo.setPayWay(null);
        }
        List<PayDetail> details =  payDetailMapper.search(searchVo);
        List<PayDetailDetailVo> result = new ArrayList<>();
        for(PayDetail payDetail:details){
            PayDetailDetailVo detailVo = new PayDetailDetailVo();
            BeanUtils.copyProperties(payDetail, detailVo);
            result.add(detailVo);
        }
        return result;
    }

    public PayDetailVo index(boolean isPersonal, int id) {

        // 查询所有的律师事务所的收支
        PayDetailVo payDetailVo = new PayDetailVo();
        List<PayDetail> details = null;
        if(isPersonal){
            details = payDetailMapper.searchAll();
        }else{
            details = payDetailMapper.searchPersonId(id);
        }
        // 分类统计
        // 分为收入 支出  收入需要查询码表 支出直接看分类
        Map<String, List<PayDetail>> incomeMap = new HashMap<>();
        for(PayDetail detail: details){
            if(detail.getIncomeType() == 1){
                if(incomeMap.containsKey("income")){
                    incomeMap.get("income").add(detail);
                }else{
                    List<PayDetail> incomeList = new ArrayList<>();
                    incomeList.add(detail);
                    incomeMap.put("income", incomeList);
                }
            }else{
                if(incomeMap.containsKey("outcome")){
                    incomeMap.get("outcome").add(detail);
                }else{
                    List<PayDetail> incomeList = new ArrayList<>();
                    incomeList.add(detail);
                    incomeMap.put("outcome", incomeList);
                }
            }
        }
        // 收入分类查询
        List<Syscode> paytypes = syscodeMapper.getSysCodeByType("payWay");
        Map<String, String> payMap = new HashMap<>();
        for(Syscode syscode : paytypes){
            payMap.put(syscode.getCodeCode(), syscode.getCodeName());
        }

        // 收入统计
        List<PayDetail> payDetails = incomeMap.get("income");
        BigDecimal income = BigDecimal.ZERO;
        StringBuilder incomeStatistics = new StringBuilder();
        Map<String, BigDecimal> incomeStatics = new HashMap<>();
        if(payDetails != null) {
            for (PayDetail payDetail : payDetails) {
                income = income.add(payDetail.getPayAmount());
                if (incomeStatics.containsKey(payDetail.getPayType())) {
                    incomeStatics.put(payDetail.getPayType(), incomeStatics.get(payDetail.getPayType()).add(payDetail.getPayAmount()));
                } else {
                    incomeStatics.put(payDetail.getPayType(), payDetail.getPayAmount());
                }
            }
        }
        for(Map.Entry<String, BigDecimal> entry : incomeStatics.entrySet()){
            incomeStatistics.append(payMap.get(entry.getKey())+entry.getValue()+";");
        }
        payDetailVo.setIncome(income);
        payDetailVo.setIncomeStatistics(incomeStatistics.toString());

        //支出统计
        List<PayDetail> outList = incomeMap.get("outcome");
        BigDecimal outcome = BigDecimal.ZERO;
        StringBuilder outStatistics = new StringBuilder();
        Map<String, BigDecimal> outComeStatics = new HashMap<>();
        if(outList != null) {
            for (PayDetail payDetail : outList) {
                outcome = outcome.add(payDetail.getPayAmount());
                if (outComeStatics.containsKey(payDetail.getPayType())) {
                    outComeStatics.put(payDetail.getPayTypeDetail(), outComeStatics.get(payDetail.getPayTypeDetail()).add(payDetail.getPayAmount()));
                } else {
                    outComeStatics.put(payDetail.getPayTypeDetail(), payDetail.getPayAmount());
                }
            }
        }

        payDetailVo.setOutCome(outcome);
        for(Map.Entry<String, BigDecimal> entry : outComeStatics.entrySet()){
            outStatistics.append(entry.getKey()+entry.getValue()+";");
        }
        payDetailVo.setOutComeStatistics(outStatistics.toString());
        return payDetailVo;
    }

    public void insertPayDetailLawyer(PayDetail payDetail) {

        payDetail.setCreateTime(LocalDate.now());
        payDetail.setIsSpecial(1);
        payDetail.setIncomeType(1);
        payDetailMapper.insert(payDetail);
    }
    public void insertPayDetailMyLawyer(PayDetail payDetail) {

        payDetail.setCreateTime(LocalDate.now());
        payDetail.setIsSpecial(0);
        payDetail.setIncomeType(1);
        payDetailMapper.insert(payDetail);
    }


    public void insertPayDetailLawyerOut(PayDetail payDetail) {
        payDetail.setCreateTime(LocalDate.now());
        payDetail.setIsSpecial(1);
        payDetail.setIncomeType(0);
        payDetailMapper.insert(payDetail);
    }

    public void insertPayDetailMyLawyerOut(PayDetail payDetail) {
        payDetail.setCreateTime(LocalDate.now());
        payDetail.setIsSpecial(0);
        payDetail.setIncomeType(0);
        payDetailMapper.insert(payDetail);
    }

    public List<PayDetailDetailVo> my(PayDetailSearchVo searchVo) {
        if(StringUtils.isBlank(searchVo.getPayType())){
            searchVo.setPayType(null);
        }
        if(StringUtils.isBlank(searchVo.getPayWay())){
            searchVo.setPayWay(null);
        }
        List<PayDetail> details =  payDetailMapper.searchPerson(searchVo);
        List<PayDetailDetailVo> result = new ArrayList<>();
        for(PayDetail payDetail:details){
            PayDetailDetailVo detailVo = new PayDetailDetailVo();
            BeanUtils.copyProperties(payDetail, detailVo);
            result.add(detailVo);
        }
        return result;
    }
}
