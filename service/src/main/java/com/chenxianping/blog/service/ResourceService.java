package com.chenxianping.blog.service;

import com.chenxianping.blog.entity.BlogResource;
import com.chenxianping.blog.vo.ResultVO;

/**
 * @author chenxp
 * @date 2022/5/5 22:14
 */
public interface ResourceService {
    ResultVO save(BlogResource resource);

    ResultVO selectById(Integer resourceId);

    ResultVO selectAll(Integer page, Integer pageSize, Integer categoryId, String keywords);

    ResultVO deleteById(Integer resourceId);
}
