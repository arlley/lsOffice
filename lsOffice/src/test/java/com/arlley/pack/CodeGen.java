package com.arlley.pack;

import com.arlley.pack.codegenerate.CodeGenerate;
import com.arlley.pack.export.ExportUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeGen {

    @Test
    public void contextLoads() {
        CodeGenerate gse = new CodeGenerate();
        //要给那些表生成
        gse.generateByTables("", new String[]{"pay_detail"});
    }

    @Test
    public void wordTest() throws IOException {
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\刑事案件收结案表.docx";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("${caseNo}", "2235");
        paramMap.put("${name}","王刚" );
        paramMap.put("${certNo}", "320982199706026113");
        paramMap.put("${punishName}", "诈骗罪");
        paramMap.put("${partyName}", "石萧");
        paramMap.put("${certNo}", "333");
        paramMap.put("${unit}", "公安局");
        paramMap.put("${stage}", "啦啦啦");
        paramMap.put("${caculateLawer}", "啦啦啦");
        paramMap.put("${lawer}","pdd");
        paramMap.put("${think}", "测试数据");
        paramMap.put("${fee}", "已经交好");
        paramMap.put("${process}", "同意");
        paramMap.put("${document}", "同意");
        paramMap.put("${note}", "你好");
        XWPFDocument document = ExportUtils.export(paramMap, path);
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\Workspace\\Work\\project\\测试.docx");
        document.write(fileOutputStream);
        fileOutputStream.close();
    }
}