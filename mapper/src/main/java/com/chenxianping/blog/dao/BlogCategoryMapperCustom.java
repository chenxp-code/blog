package com.chenxianping.blog.dao;

import com.chenxianping.blog.entity.BlogCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCategoryMapperCustom {

    List<BlogCategory> selectAllForPage(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
}