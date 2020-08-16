package com.arlley.pack.mapper;

import com.arlley.pack.entity.CaseLawyer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanggang
 * @since 2020-08-07
 */
@Mapper
@Repository
public interface CaseLawyerMapper extends BaseMapper<CaseLawyer> {


    List<String> selectByCaseId(@Param("caseId") int caseId);
}
