package com.cs.springbootcrud.controller;

import com.alibaba.fastjson.JSON;
import com.cs.springbootcrud.annotation.DemoArgument;
import com.cs.springbootcrud.dto.MSkuEntityDto;
import com.cs.springbootcrud.model.MSkuEntity;
import com.cs.springbootcrud.service.DemoService;
import com.cs.springbootcrud.vo.GetByIdVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@Slf4j
public class DemoController {

    @Resource
    DemoService demoServiceImpl;

    @GetMapping("/getById")
    @ApiOperation("sku")
    public MSkuEntity getById(HttpServletRequest request ,@RequestParam("id")Long id){
        return demoServiceImpl.getById(id);
    }

    @PostMapping("/get")
    @ApiOperation("sku2")
    public MSkuEntity getById(HttpServletRequest request , @DemoArgument @ApiIgnore MSkuEntityDto mSkuEntityDto,@RequestBody GetByIdVo getByIdVo){
        System.out.println(mSkuEntityDto);
        log.info("mSkuEntityDto:{}"+ JSON.toJSONString(mSkuEntityDto));
        return demoServiceImpl.getById(getByIdVo.getId());
    }
}
