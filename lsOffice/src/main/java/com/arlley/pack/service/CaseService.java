package com.arlley.pack.service;

import com.arlley.pack.entity.Case;
import com.arlley.pack.entity.Pay;
import com.arlley.pack.mapper.CaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {

    @Autowired
    private CaseMapper caseMapper;

    public List<Case> getAllCase(){
        return caseMapper.selectAllCase();
    }

    public void insert(Case cass){
        caseMapper.insert(cass);
    }

    public void update(Case cass){
        caseMapper.updateById(cass);
    }

    public void delete(int id){
        caseMapper.deleteById(id);
    }
}
