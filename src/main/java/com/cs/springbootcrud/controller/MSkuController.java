package com.cs.springbootcrud.controller;

import com.cs.springbootcrud.model.MSkuEntity;
import com.cs.springbootcrud.service.MSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MSkuController
 * @Description TODO
 * @Auther chenss
 * @Date 2020/3/30 9:11 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
@Api(tags = "测试接口")
public class MSkuController {

    @Resource
    MSkuService  mSkuServiceImpl;

    @GetMapping("/getById")
    @ApiOperation("sku")
    public MSkuEntity getById(@RequestParam("id")Long id){
        return mSkuServiceImpl.getById(id);
    }
}
