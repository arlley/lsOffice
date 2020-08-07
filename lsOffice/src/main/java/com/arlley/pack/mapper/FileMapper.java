package com.arlley.pack.mapper;

import com.arlley.pack.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanggang
 * @since 2020-08-02
 */
@Mapper
@Repository
public interface FileMapper extends BaseMapper<File> {

}
