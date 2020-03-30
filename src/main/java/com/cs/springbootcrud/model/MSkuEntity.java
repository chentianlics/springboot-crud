package com.cs.springbootcrud.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName MSkuEntity
 * @Description TODO
 * @Auther chenss
 * @Date 2020/3/30 9:05 AM
 * @Version 1.0
 **/
@Data
public class MSkuEntity {
    @ApiModelProperty("sku的View")
    private Long viewId;
    @ApiModelProperty("小程序spu表中id")
    private Long spuId;
    @ApiModelProperty("渠道id")
    private Long tenantId;
    @ApiModelProperty("销售价格")
    private BigDecimal salePrice;
    @ApiModelProperty("原始价格")
    private BigDecimal originalPrice;
    @ApiModelProperty("库存")
    private Long stock;
    @ApiModelProperty("商品多规格标识")
    private String skuCode;
    @ApiModelProperty("商品描述")
    private String descri;
    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("城市id")
    private Long cityId;
    @ApiModelProperty("sku分类")
    private Long categoryId;

    @ApiModelProperty("规格名称")
    private String specs;

    @ApiModelProperty("当前库存")
    private Long currentStock;

    @ApiModelProperty("最大库存")
    private Long maxStock;

    @ApiModelProperty("是否次日置满，0否；1是")
    private Integer nextDayFull;

    @ApiModelProperty("新增同一批次商品id标志")
    private Long sameBatchId;

    private Long saasSpuId;

    protected Long id;
    protected int status;
    // 创建时间
    protected Date createTime;
    // 最后修改时间
    protected Date updateTime;
}
