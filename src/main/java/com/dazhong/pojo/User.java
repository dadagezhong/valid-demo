package com.dazhong.pojo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {

    @NotBlank(message = "姓名不能为空")
    private String username;
    @NotBlank(message = "密码不为空")
    private String password;
    @Valid
    @NotNull(message = "userInfo不能为空")
    private UserInfo userInfo;
}
