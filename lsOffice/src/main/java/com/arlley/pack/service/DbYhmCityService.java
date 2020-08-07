package com.arlley.pack.service;

import com.arlley.pack.entity.DbYhmCity;
import com.arlley.pack.mapper.DbYhmCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbYhmCityService {

    @Autowired
    private DbYhmCityMapper dbYhmCityMapper;


    public List<DbYhmCity> getByClassType(int classType){
        return dbYhmCityMapper.selectByClassType(classType);
    }
}
