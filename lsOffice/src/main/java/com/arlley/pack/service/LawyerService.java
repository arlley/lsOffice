package com.arlley.pack.service;

import com.arlley.pack.entity.*;
import com.arlley.pack.mapper.*;
import com.arlley.pack.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LawyerService {

    @Autowired
    private LawerMapper lawerMapper;

    @Autowired
    private EduMapper eduMapper;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private RewordMapper rewordMapper;

    @Autowired
    private PunishMapper punishMapper;

    public void insertLawyer(LawyerVo lawyerVo){
        // 保存主表

        Lawer lawer = new Lawer();
        BeanUtils.copyProperties(lawyerVo, lawer);
        lawerMapper.insert(lawer);

        if(lawyerVo.getEduList() != null && lawyerVo.getEduList().size() > 0){
            // 保存教育经历
            for(EduVo eduVo : lawyerVo.getEduList()){
                Edu edu = new Edu();
                BeanUtils.copyProperties(eduVo, edu);
                edu.setLid(lawer.getId());
                eduMapper.insert(edu);
            }
        }

        if(lawyerVo.getWorkList() != null && lawyerVo.getWorkList().size() > 0){
            for(WorkVo workVo : lawyerVo.getWorkList()){
                Work work = new Work();
                BeanUtils.copyProperties(workVo, work);
                work.setLid(lawer.getId());
                workMapper.insert(work);
            }
        }

        if(lawyerVo.getRewordList() != null && lawyerVo.getRewordList().size() > 0){
            for(RewardVo rewardVo : lawyerVo.getRewordList()){
                Reword reword = new Reword();
                BeanUtils.copyProperties(rewardVo, reword);
                reword.setLid(lawer.getId());
                rewordMapper.insert(reword);
            }
        }

        if(lawyerVo.getPunishList() != null && lawyerVo.getPunishList().size() > 0){
            for(PunishVo punishVo : lawyerVo.getPunishList()){
                Punish punish = new Punish();
                BeanUtils.copyProperties(punishVo, punish);
                punish.setLid(lawer.getId());
                punishMapper.insert(punish);
            }
        }
    }

    public List<LawyerVo> query(LawyerSearchVo lawyerSearchVo){

        if(StringUtils.isBlank(lawyerSearchVo.getName())){
            lawyerSearchVo.setName(null);
        }

        if(StringUtils.isBlank(lawyerSearchVo.getIdentity())){
            lawyerSearchVo.setIdentity(null);
        }

        List<Lawer> lawers = lawerMapper.selectAll(lawyerSearchVo);

        List<LawyerVo> lawyerVos = new ArrayList<>();
        for(Lawer lawer : lawers){
            LawyerVo lawyerVo = new LawyerVo();
            BeanUtils.copyProperties(lawer, lawyerVo);
            lawyerVos.add(lawyerVo);
        }
        return lawyerVos;
    }
}
