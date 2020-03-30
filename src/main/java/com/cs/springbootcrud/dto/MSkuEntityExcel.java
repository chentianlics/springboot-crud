package com.cs.springbootcrud.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName MSkuEntityExcel
 * @Description TODO
 * @Auther chenss
 * @Date 2020/3/30 3:05 PM
 * @Version 1.0
 **/
@Data
public class MSkuEntityExcel {

    @ApiModelProperty("销售价格")
    @Excel(name = "销售价格", orderNum = "0")
    private BigDecimal salePrice;

    @ApiModelProperty("原始价格")
    @Excel(name = "原始价格", orderNum = "1")
    private BigDecimal originalPrice;

    @Excel(name = "库存", orderNum = "2")
    @ApiModelProperty("库存")
    private Long stock;

    @Excel(name = "商品名称", orderNum = "4")
    @ApiModelProperty("商品名称")
    private String name;

    @Excel(name = "创建时间",exportFormat = "yyyy-MM-dd", orderNum = "5")
    private Date createTime;

}
