package com.cs.springbootcrud.mapper;

import com.cs.springbootcrud.model.MSkuEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MSkuMapper {

    MSkuEntity getById(Long id);
}
