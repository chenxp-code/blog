package com.chenxianping.blog.dao;

import com.chenxianping.blog.entity.BlogLink;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlogLinkMapperCustom {

    List<BlogLink> selectAllForPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize,
                                    @Param("linkSource") Integer linkSource, @Param("status") Integer status,
                                    @Param("keywords") String keywords);
}