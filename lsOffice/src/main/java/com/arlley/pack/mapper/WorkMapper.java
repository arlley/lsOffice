package com.arlley.pack.mapper;

import com.arlley.pack.entity.Work;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
public interface WorkMapper extends BaseMapper<Work> {

}
