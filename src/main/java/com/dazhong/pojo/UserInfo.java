package com.dazhong.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class UserInfo {

    @NotBlank(message = "年龄不能为空")
    @Max(value = 18,message = "年龄不能超过18")
    private String age;
    @NotBlank(message = "性别不能为空")
    private String gender;
}
