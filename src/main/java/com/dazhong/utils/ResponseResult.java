package com.dazhong.utils;

import com.dazhong.enums.ResultEnum;
import lombok.Data;

@Data
public class ResponseResult {

    private Integer code;
    private String message;

    public ResponseResult(){
    }

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
}
