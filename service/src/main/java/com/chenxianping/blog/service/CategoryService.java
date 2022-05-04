package com.chenxianping.blog.service;

import com.chenxianping.blog.entity.BlogCategory;
import com.chenxianping.blog.vo.ResultVO;

/**
 * @author chenxp
 * @date 2022/5/4 14:51
 */
public interface CategoryService {

    ResultVO save(BlogCategory blogCategory);

    ResultVO selectById(Integer categoryId);

    ResultVO deleteById(Integer categoryId);

    ResultVO selectAll(Integer page, Integer pageSize);
}
