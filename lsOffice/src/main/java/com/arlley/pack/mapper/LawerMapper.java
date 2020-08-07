package com.arlley.pack.mapper;

import com.arlley.pack.entity.Lawer;
import com.arlley.pack.vo.LawyerSearchVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanggang
 * @since 2020-08-03
 */
@Mapper
@Repository
public interface LawerMapper extends BaseMapper<Lawer> {

    List<Lawer> selectAll(LawyerSearchVo lawyerSearchVo);

}
