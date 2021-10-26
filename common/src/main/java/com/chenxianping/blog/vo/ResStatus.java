package com.chenxianping.blog.vo;

public class ResStatus {

    /**
     * 响应状态码
     */
    public static final int OK = 10000;
    public static final int NO = 10001;

    public static final int LOGIN_SUCCESS = 20000;  //认证成功
    public static final int LOGIN_FAIL_NOT = 20001; //用户未登录
    public static final int LOGIN_FAIL_OVERDUE = 20002; //用户登录失效

}