package com.arlley.pack.mapper;

import com.arlley.pack.entity.DbYhmCity;
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
 * @since 2020-08-01
 */
@Mapper
@Repository
public interface DbYhmCityMapper extends BaseMapper<DbYhmCity> {

    DbYhmCity selectByClassId(@Param("classId") int classId);

    List<DbYhmCity> selectByClassType(@Param("classType") int classType);
}
