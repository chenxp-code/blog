package com.chenxianping.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author chenxp
 * @date 2022/5/9 19:50
 */
@Data
@AllArgsConstructor
public class AdminLoginDTO {

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    private String userPassword;
}
