package com.arlley.pack.mapper;

import com.arlley.pack.entity.QianchengUserTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author wanggang
 * @since 2020-07-20
 */
@Repository
@Mapper
public interface QianchengUserTableMapper extends BaseMapper<QianchengUserTable> {


    public QianchengUserTable selectByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
