package com.cs.springbootcrud.controller;

import com.cs.springbootcrud.model.MSkuEntity;
import com.cs.springbootcrud.service.MSkuService;
import com.cs.springbootcrud.vo.GetByIdVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public MSkuEntity getById(HttpServletRequest request ,@RequestParam("id")Long id){
        return mSkuServiceImpl.getById(id);
    }

    @PostMapping("/get")
    @ApiOperation("sku2")
    public MSkuEntity getById(HttpServletRequest request , @RequestBody GetByIdVo getByIdVo){
        return mSkuServiceImpl.getById(getByIdVo.getId());
    }
}
