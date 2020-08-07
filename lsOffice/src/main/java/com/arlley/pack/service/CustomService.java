package com.arlley.pack.service;

import com.arlley.pack.entity.Custom;
import com.arlley.pack.entity.DbYhmCity;
import com.arlley.pack.mapper.CustomMapper;
import com.arlley.pack.mapper.DbYhmCityMapper;
import com.arlley.pack.vo.CustomVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomService {

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private DbYhmCityMapper dbYhmCityMapper;

    public List<CustomVo> selectList(CustomVo params){
        List<Custom> customList = null;
        if(params == null) {
            customList = customMapper.selectAll();
        }else{
            if(StringUtils.isBlank(params.getName())){
                params.setName(null);
            }
            if(StringUtils.isBlank(params.getTel())){
                params.setTel(null);
            }
            if(StringUtils.isBlank(params.getAssign())){
                params.setAssign(null);
            }
            if(StringUtils.isBlank(params.getType())){
                params.setType(null);
            }
            customList = customMapper.selectBySearch(params);
        }
        List<CustomVo> result = new ArrayList<>();
        if(customList == null || customList.size() == 0){
            return result;
        }

        for(Custom custom : customList){
            CustomVo customVo = new CustomVo();
            BeanUtils.copyProperties(custom, customVo);
            customVo.setAssign("1".equals(custom.getAssign()) ? "是":"否");
            if(StringUtils.isNotBlank(custom.getPro()) && StringUtils.isNotBlank(custom.getCity())) {
                customVo.setRegion(getRegion(Integer.parseInt(custom.getPro()), Integer.parseInt(custom.getCity())));
            }
            result.add(customVo);
        }
        return result;
    }

    public void insert(Custom custom){
        custom.setCreateTime(new Date());
        custom.setAssign("0");
        customMapper.insert(custom);
    }

    public void update(Custom custom){
        customMapper.updateById(custom);
    }

    public void delete(int id){
        customMapper.deleteById(id);
    }

    public Custom getById(int id){
        return customMapper.selectById(id);
    }

    private String getRegion(int proType, int cityType){
        DbYhmCity pro = dbYhmCityMapper.selectByClassId(proType);
        DbYhmCity city = dbYhmCityMapper.selectByClassId(cityType);
        return pro.getClassName() + "省" + city.getClassName() + "市";
    }
}
