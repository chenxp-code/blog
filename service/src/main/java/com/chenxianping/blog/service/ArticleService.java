package com.chenxianping.blog.service;

import com.chenxianping.blog.entity.BlogArticle;
import com.chenxianping.blog.vo.ResultVO;

public interface ArticleService {

    //发布文章
    ResultVO saveArticle(BlogArticle blogArticle);

    //修改文章
    ResultVO updateArticle(BlogArticle blogArticle);

    //删除文章
    ResultVO deleteArticle(Integer articleId);

    //批量删除文章
    ResultVO deleteArticles(Integer[] articleIds);

    //根据Id查文章
    ResultVO getArticleById(Integer articleId);

    //关键字查询
    ResultVO getArticlesByKeyword(String keyword);

}
