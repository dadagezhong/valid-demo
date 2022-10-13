package com.dazhong.controller;

import com.dazhong.enums.ResultEnum;
import com.dazhong.exceptions.ParamaErrorException;
import com.dazhong.pojo.User;
import com.dazhong.utils.ResponseResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TestValidController {

    /**
     * 获取用户信息
     *
     * @param username 姓名
     * @return ResponseResult
     */
    @Validated
    @GetMapping(value = "/user/{username}")
    public ResponseResult findUserInfo(@PathVariable String username) {
        if (username == null || "".equals(username)) {
            throw new ParamaErrorException("username 不能为空");
        }
        return new ResponseResult(ResultEnum.SUCCESS);
    }


    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @PostMapping("/user")
    public ResponseResult addUserInfo(@Valid @RequestBody User user) {
        return new ResponseResult(ResultEnum.SUCCESS);
    }

}