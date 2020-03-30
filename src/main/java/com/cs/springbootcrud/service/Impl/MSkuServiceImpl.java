package com.cs.springbootcrud.service.Impl;

import com.cs.springbootcrud.dto.MSkuEntityExcel;
import com.cs.springbootcrud.mapper.MSkuMapper;
import com.cs.springbootcrud.model.MSkuEntity;
import com.cs.springbootcrud.service.DemoService;
import com.cs.springbootcrud.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MSkuServiceImpl
 * @Description TODO
 * @Auther chenss
 * @Date 2020/3/30 9:01 AM
 * @Version 1.0
 **/
@Service
@Slf4j
public class MSkuServiceImpl implements DemoService {
    @Autowired
    private MSkuMapper mSkuMapper;

    @Override
    public MSkuEntity getById(Long id){
        return mSkuMapper.getById(id);
    }

    @Override
    public void  export(Long id, HttpServletResponse response){
        MSkuEntity mSkuEntity = mSkuMapper.getById(id);
        MSkuEntityExcel mSkuEntityExcel = new MSkuEntityExcel();
        BeanUtils.copyProperties(mSkuEntity,mSkuEntityExcel);
        List<MSkuEntityExcel> mSkuEntityExcelList = new ArrayList<>();
        mSkuEntityExcelList.add(mSkuEntityExcel);
        //导出数据
        ExcelUtils.exportExcel(mSkuEntityExcelList,"标题","sheet名称",MSkuEntityExcel.class,"demo",true,response);
    }
}
