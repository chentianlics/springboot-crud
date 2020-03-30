package com.cs.springbootcrud.controller;

import com.cs.springbootcrud.dto.MSkuEntityExcel;
import com.cs.springbootcrud.model.MSkuEntity;
import com.cs.springbootcrud.service.DemoService;
import com.cs.springbootcrud.util.FileUtils;
import com.cs.springbootcrud.util.WordUtils;
import com.cs.springbootcrud.util.ZipUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName ExportController
 * @Description TODO
 * @Auther chenss
 * @Date 2020/3/30 2:41 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/export")
@Api(tags = "导出接口")
@Slf4j
public class ExportController {

    @Resource
    DemoService demoServiceImpl;

    @ApiOperation("导出excel")
    @GetMapping("/excel")
    public void exportReferralOutRecord(@RequestParam Long id, HttpServletResponse response){
        demoServiceImpl.export(id,response);
        //referralOrderService.exportReferralExcel(idList,response);
    }

    @ApiOperation("导出word1")
    @GetMapping("/word1")
    public void word(@RequestParam String name, HttpServletResponse response){

        //防止文件名乱码
        try {
            name = java.net.URLDecoder.decode(name,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //获取系统资源目录
        String path = this.getClass().getClassLoader().getResource("application.yml").getPath();
        log.info("path: " + path);

        String wordPath = FileUtils.getPath(path,FileUtils.WORD_PATH);

        Map<String, Object> param = new HashMap<>(1);
        param.put("name", name);

        String informedConsentPath = "templates/name.docx";
        WordUtils.exportWord(informedConsentPath, wordPath, "name.docx", param, response);
    }

    @ApiOperation("导出word2")
    @GetMapping("/word2")
    public void exportReferralApplyOrderWord(@RequestParam("id") Long id , HttpServletResponse response){

        //获取系统资源目录
        String path = this.getClass().getClassLoader().getResource("application.yml").getPath();
        log.info("path:   " + path);
        String wordPath = FileUtils.getPath(path,FileUtils.WORD_PATH);
        MSkuEntity mSkuEntity = demoServiceImpl.getById(id);
        MSkuEntityExcel  mSkuEntityExcel = new MSkuEntityExcel();
        BeanUtils.copyProperties(mSkuEntity,mSkuEntityExcel);
        Map<String, Object> param = buildMkuEntityExcelForWord(mSkuEntityExcel);
        String templatePath = "templates/name.docx";
        String fileName = null;
        try {
            fileName = new String(mSkuEntityExcel.getName().getBytes(),"utf-8") + DateFormatUtils.format(new Date(), "yyyyMMdd_HHmmssSSS") + ".docx";
        } catch (UnsupportedEncodingException e) {
            log.error("=================文件名转换异常");
            e.printStackTrace();
        }
        WordUtils.exportWord(templatePath, wordPath, fileName, param, response);
    }

    @ApiOperation("导出zip")
    @GetMapping("/zip")
    public void zip(@RequestParam String name, HttpServletResponse response){

        //mock数据
        List<MSkuEntityExcel> mSkuEntityExcelList = new ArrayList<>();

        //获取系统资源目录
        String path = this.getClass().getClassLoader().getResource("application.yml").getPath();
        log.info("path:   " + path);
        String zipPath= FileUtils.getPath(path,FileUtils.ZIP_PATH);
        String wordPath=FileUtils.getPath(path,FileUtils.WORD_PATH);
        for(MSkuEntityExcel mkuEntityExcel:mSkuEntityExcelList){
            Map<String, Object> param = buildMkuEntityExcelForWord(mkuEntityExcel);
            WordUtils.saveWord(wordPath,param,mkuEntityExcel.getName());
        }
        ZipUtils.saveZip(wordPath,zipPath, DateFormatUtils.format(new Date(), "yyyyMMdd_HHmmssSSS"),response);

    }
    private Map<String, Object> buildMkuEntityExcelForWord(MSkuEntityExcel mkuEntityExcel){
        Map<String, Object> param =new HashMap<>();
        param.put("name", mkuEntityExcel.getName());
        return param;
    }

}
