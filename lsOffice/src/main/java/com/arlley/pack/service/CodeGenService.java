package com.arlley.pack.service;

import com.arlley.pack.constant.Constants;
import com.arlley.pack.entity.Codegen;
import com.arlley.pack.mapper.CodegenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class CodeGenService {

    @Autowired
    private CodegenMapper codegenMapper;

    public String getNoBypNo(String pNo){
        synchronized (Constants.CODE_GEN_LOCK){
            Codegen codegen = codegenMapper.selectBypNo(pNo);
            int num = codegen.getMaxNum() + 1;
            codegen.setMaxNum(num);
            codegenMapper.updateById(codegen);
            return genCode(pNo, num, codegen.getNumLength());
        }
    }

    private String genCode(String pNo, int num, Integer numLength) {
        // 计算出需要补足的0的个数
        int digit = ((int)Math.log10(num)) + 1;
        StringBuilder sb = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        sb.append(calendar.get(Calendar.YEAR));
        sb.append(",");
        int index = 0;
        while (index < numLength - digit){
            sb.append(0);
            index ++;
        }
        sb.append(num);
        return sb.toString();
    }
}
