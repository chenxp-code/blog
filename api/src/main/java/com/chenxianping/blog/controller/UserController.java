package com.chenxianping.blog.controller;

import com.chenxianping.blog.dto.AdminLoginDTO;
import com.chenxianping.blog.service.UserService;
import com.chenxianping.blog.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(value = "提供用户相关的操作接口",tags = "用户管理")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("添加管理员接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "userName", value = "管理员账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "userPassword", value = "管理员密码",required = true),
            @ApiImplicitParam(dataType = "byte",name = "userType", value = "管理员类型",required = true)
    })
    @PostMapping("/saveAdmin")
    public ResultVO addAdmin(String userName,String userPassword,Byte userType){
        return userService.addAdmin(userName, userPassword,userType);
    }

    @ApiOperation("管理员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "userName", value = "管理员账号",required = false),
            @ApiImplicitParam(dataType = "string",name = "userPassword", value = "管理员密码",required = false)
    })
    @GetMapping("/loginAdmin")
//    public ResultVO loginAdmin(String userName,String userPassword){
//        return userService.loginAdmin(userName, userPassword);
//    }
    public ResultVO loginAdmin(@Validated AdminLoginDTO user, BindingResult result){
        return userService.loginAdmin(user.getUserName(), user.getUserPassword());
    }

    @ApiOperation("修改管理员密码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "userPassword", value = "管理员密码",required = true),
            @ApiImplicitParam(dataType = "string",name = "newPassword", value = "管理员新密码",required = true)
    })
    @PutMapping("/updateAdmin/{id}")
    public ResultVO loginAdmin(@PathVariable("id")Integer id, String userPassword, String newPassword){
        return userService.updatePassword(id,userPassword,newPassword);
    }
}
