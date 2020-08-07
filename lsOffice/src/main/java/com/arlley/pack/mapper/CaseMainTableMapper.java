package com.arlley.pack.mapper;

import com.arlley.pack.entity.CaseMainTable;
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
 * @since 2020-08-02
 */
@Mapper
@Repository
public interface CaseMainTableMapper extends BaseMapper<CaseMainTable> {


    List<CaseMainTable> searchAll();
}
