package com.chenxianping.blog.dao;

import com.chenxianping.blog.entity.BlogCarousel;
import com.chenxianping.blog.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogCarouselMapperCustom {
    List<BlogCarousel> selectWithCondition(@Param("type") Integer type, @Param("showStatus") Integer showStatus);
}