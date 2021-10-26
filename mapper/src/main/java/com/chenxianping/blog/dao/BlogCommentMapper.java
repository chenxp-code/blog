package com.chenxianping.blog.dao;

import com.chenxianping.blog.entity.BlogComment;
import com.chenxianping.blog.general.GeneralDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCommentMapper extends GeneralDAO<BlogComment> {
}