package com.cs.springbootcrud.model.base;

import com.cs.springbootcrud.constant.ResultEnum;
import com.github.pagehelper.util.StringUtil;
import lombok.Data;

/**
 * @ClassName Result
 * @Description TODO
 * @Auther chenss
 * @Date 2020/3/30 1:14 PM
 * @Version 1.0
 **/
@Data
public class Result<D> {
    private int code;
    private String msg;
    private D data;

    public Result() {}

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, D data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultEnum returnCodeEnum) {
        this.code = returnCodeEnum.getValue();
        this.msg = returnCodeEnum.getDisplay();
    }

    public Result(ResultEnum returnCodeEnum, String msg, D data) {
        this.code = returnCodeEnum.getValue();
        if(StringUtil.isNotEmpty(msg)) {
            this.msg = msg;
        }else {
            this.msg = returnCodeEnum.getDisplay();
        }
    }

    public Result(ResultEnum returnCodeEnum, D data) {
        this.code = returnCodeEnum.getValue();
        this.msg = returnCodeEnum.getDisplay();
        this.data = data;
    }
}
