package com.arlley.pack.mapper;

import com.arlley.pack.entity.Codegen;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanggang
 * @since 2020-07-28
 */
@Repository
@Mapper
public interface CodegenMapper extends BaseMapper<Codegen> {

    Codegen selectBypNo(@Param("pNo") String pNo);
}
