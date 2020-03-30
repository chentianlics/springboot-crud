package com.cs.springbootcrud.mapper;

import com.cs.springbootcrud.model.MSkuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MSkuMapper {

    @Select({"SELECT * from m_sku where id = #{id}"})
    MSkuEntity getById(Long id);
}
