package com.arlley.pack.service;

import com.arlley.pack.constant.Constants;
import com.arlley.pack.entity.Codegen;
import com.arlley.pack.mapper.CodegenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        sb.append(pNo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        sb.append(sdf.format(new Date()));
        int index = 0;
        while (index < numLength - digit){
            sb.append(0);
        }
        sb.append(num);
        return sb.toString();
    }
}
