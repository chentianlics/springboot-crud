package com.cs.springbootcrud.service.Impl;

import com.cs.springbootcrud.mapper.MSkuMapper;
import com.cs.springbootcrud.model.MSkuEntity;
import com.cs.springbootcrud.service.MSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MSkuServiceImpl
 * @Description TODO
 * @Auther chenss
 * @Date 2020/3/30 9:01 AM
 * @Version 1.0
 **/
@Service
@Slf4j
public class MSkuServiceImpl implements MSkuService {
    @Autowired
    private MSkuMapper mSkuMapper;

    @Override
    public MSkuEntity getById(Long id){
        return mSkuMapper.getById(id);
    }
}
