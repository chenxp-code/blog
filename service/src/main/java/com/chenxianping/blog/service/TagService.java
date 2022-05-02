package com.chenxianping.blog.service;

import com.chenxianping.blog.entity.BlogTag;
import com.chenxianping.blog.vo.ResultVO;

/**
 * @author chenxp
 * @date 2022/5/2 14:15
 */
public interface TagService {
    ResultVO save(BlogTag tag);

    ResultVO selectById(Integer tagId);

    ResultVO selectAll(Integer page, Integer pageSize);

    ResultVO deleteById(Integer tagId);
}
