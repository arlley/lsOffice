package com.arlley.pack.mapper;

import com.arlley.pack.entity.Syscode;
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
 * @since 2020-07-27
 */
@Mapper
@Repository
public interface SyscodeMapper extends BaseMapper<Syscode> {

    Syscode getSysCodeByTypeAndCode(@Param("type") String type, @Param("code") String code);

    List<Syscode> getSysCodeByType(@Param("type") String type);
}
