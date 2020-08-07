package com.arlley.pack.mapper;

import com.arlley.pack.entity.Custom;
import com.arlley.pack.vo.CustomVo;
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
 * @since 2020-08-01
 */

@Mapper
@Repository
public interface CustomMapper extends BaseMapper<Custom> {

    List<Custom> selectAll();

    List<Custom> selectBySearch(CustomVo customVo);
}
