package com.dazhong.exceptions;

import com.dazhong.enums.ResultEnum;
import com.dazhong.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@Slf4j
@RestControllerAdvice("com.dazhong")  //指定异常处理的包名
public class GlobalExceptionHandler {

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        log.error("", e);
        return new ResponseResult(ResultEnum.PARAMETER_ERROR.getCode(), "请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e){
        log.error("", e);
        return new ResponseResult(ResultEnum.PARAMETER_ERROR.getCode(),  "参数体不能为空");

    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult parameterExceptionHandler(MethodArgumentNotValidException e){
        log.error("", e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return new ResponseResult(ResultEnum.PARAMETER_ERROR.getCode(), fieldError.getDefaultMessage());
            }
        }
        return new ResponseResult(ResultEnum.PARAMETER_ERROR);
    }

    /**
     * 自定义参数错误异常处理器
     *
     * @param e 自定义参数
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ParamaErrorException.class})
    public ResponseResult paramExceptionHandler(ParamaErrorException e) {
        log.error("", e);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (!StringUtils.isEmpty(e.getMessage())) {
            return new ResponseResult(ResultEnum.PARAMETER_ERROR.getCode(), e.getMessage());
        }
        return new ResponseResult(ResultEnum.PARAMETER_ERROR);
    }
}
