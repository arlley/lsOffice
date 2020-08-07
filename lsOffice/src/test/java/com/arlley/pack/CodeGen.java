package com.arlley.pack;

import com.arlley.pack.codegenerate.CodeGenerate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

public class CodeGen {
    @Test
    public void contextLoads() {
        CodeGenerate gse = new CodeGenerate();
        //要给那些表生成
        gse.generateByTables("", new String[]{"case_lawyer"});
    }
}