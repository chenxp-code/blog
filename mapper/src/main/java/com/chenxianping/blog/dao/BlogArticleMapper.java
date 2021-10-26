package com.chenxianping.blog.dao;

import com.chenxianping.blog.entity.BlogArticle;
import com.chenxianping.blog.general.GeneralDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogArticleMapper extends GeneralDAO<BlogArticle> {
}