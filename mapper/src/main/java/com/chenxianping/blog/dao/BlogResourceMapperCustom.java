package com.chenxianping.blog.dao;

import com.chenxianping.blog.entity.BlogResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogResourceMapperCustom{

    List<BlogResource> selectAllForPage(@Param("offset")Integer offset, @Param("pageSize")Integer pageSize,
                                        @Param("categoryId")Integer categoryId, @Param("keywords")String keywords);
}