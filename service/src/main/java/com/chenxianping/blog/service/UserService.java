package com.chenxianping.blog.service;

import com.chenxianping.blog.vo.ResultVO;

public interface UserService {

    //添加管理员账号
    ResultVO addAdmin(String userName,String userPassword,Byte userType);

    //登录管理员账号
    ResultVO loginAdmin(String userName,String userPassword);

    ResultVO updatePassword(Integer id,String userPassword,String newPassword);
}
