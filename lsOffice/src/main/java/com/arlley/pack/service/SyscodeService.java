package com.arlley.pack.service;

import com.arlley.pack.entity.Syscode;
import com.arlley.pack.mapper.SyscodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyscodeService {

    @Autowired
    private SyscodeMapper syscodeMapper;

    public Syscode getSyscodeByTypeAndCode(String codeType, String codeCode){
        return syscodeMapper.getSysCodeByTypeAndCode(codeType, codeCode);
    }

    public List<Syscode> getSyscodeByType(String codeType){
        return syscodeMapper.getSysCodeByType(codeType);
    }
}
