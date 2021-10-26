package com.chenxianping.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/article")
@Api(value = "提供文章相关的操作接口",tags = "文章管理")
public class ArticleController {


}
