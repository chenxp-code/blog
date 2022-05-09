package com.chenxianping.blog.vo;

public class ResStatus {

    /**
     * 响应状态码
     */
    public static final int OK = 200; //操作成功
    public static final int NO = 400; //操作失败

    public static final int UNAUTHORIZED = 401; //未登录或登录失效

    public static final int FORBIDDEN = 403; //权限不足

    public static final int VALIDATE_FAILED = 422; //数据验证失败

}