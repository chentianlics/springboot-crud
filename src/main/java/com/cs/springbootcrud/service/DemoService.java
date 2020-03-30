package com.cs.springbootcrud.service;


import com.cs.springbootcrud.model.MSkuEntity;
import com.cs.springbootcrud.util.ExcelUtils;

import javax.servlet.http.HttpServletResponse;

public interface DemoService {

    MSkuEntity getById(Long id);

    void  export(Long id,HttpServletResponse response);

}
