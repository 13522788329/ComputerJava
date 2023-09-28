package com.pla.Utils;

import com.pla.Enum.ResultEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {
    private static final long serialVersionUID = 8582093728816173504L;
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<String, Object>();

    private Result() {

    }

    public Result(String key, Object value) {
        this.success = true;
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.data.put(key, value);
    }

    public Result(String key, Object value, String message) {
        this.success = true;
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = message;
        this.data.put(key, value);
    }

    public Result(Map<String, Object> map) {
        this.success = true;
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.data = map;
    }

    public Result(Map<String, Object> map, String message) {
        this.success = true;
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = message;
        this.data = map;
    }

    public Result(String failMessage) {
        this.success = false;
        this.code = ResultEnum.FAIL.getCode();
        this.message = ResultEnum.FAIL.getMessage();
    }
}
