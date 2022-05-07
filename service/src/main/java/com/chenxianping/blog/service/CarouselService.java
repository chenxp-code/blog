package com.chenxianping.blog.service;

import com.chenxianping.blog.entity.BlogCarousel;
import com.chenxianping.blog.vo.ResultVO;

/**
 * @author chenxp
 * @date 2022/5/7 10:23
 */
public interface CarouselService {
    ResultVO save(BlogCarousel carousel);

    ResultVO selectById(Integer carouselId);

    ResultVO deleteById(Integer carouselId);

    ResultVO switchShowById(Integer carouselId);

    ResultVO selectAllIsShow();

    ResultVO selectAll(Integer type, Integer showStatus);
}
