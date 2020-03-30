package com.cs.springbootcrud.constant;

import java.util.HashMap;
import java.util.Map;

public enum ResultEnum implements IBaseDataEnum {

    SUCCEED(200, "成功"),
    FAIL(500,"失败"),
    NOT_LOGIN(600,"未登录"),
    NOT_AUTH(700,"未授权");

    private Integer value;

    private String display;

    private static Map<Integer, ResultEnum> valueMap = new HashMap<>();

    static {
        for (ResultEnum e : ResultEnum.values()) {
            valueMap.put(e.value, e);
        }
    }

    ResultEnum(Integer value, String display) {
        this.value = value;
        this.display = display;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getDisplay() {
        return this.display;
    }

    public static ResultEnum getByValue(Integer value) {
        ResultEnum result = valueMap.get(value);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + value);
        }
        return result;
    }
}
