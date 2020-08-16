package com.arlley.pack.mapper;

import com.arlley.pack.entity.PayDetail;
import com.arlley.pack.vo.PayDetailSearchVo;
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
 * @since 2020-08-10
 */
@Mapper
@Repository
public interface PayDetailMapper extends BaseMapper<PayDetail> {

    List<PayDetail> search(PayDetailSearchVo searchVo);


    List<PayDetail> searchAll();

    List<PayDetail> searchPersonId(@Param("id") int id);

    List<PayDetail> searchPerson(PayDetailSearchVo searchVo);
}
