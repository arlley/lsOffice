package com.arlley.pack.mapper;

import com.arlley.pack.entity.Pay;
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
 * @since 2020-07-27
 */
@Mapper
@Repository
public interface PayMapper extends BaseMapper<Pay> {


    List<Pay> selectAllPay();
}
