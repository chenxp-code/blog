package com.chenxianping.blog.dao;

import com.chenxianping.blog.entity.BlogTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTagMapperCustom {
    List<BlogTag> selectAllForPage(@Param("offset")Integer offset,@Param("pageSize")Integer pageSize, @Param("keywords")String keywords);
}