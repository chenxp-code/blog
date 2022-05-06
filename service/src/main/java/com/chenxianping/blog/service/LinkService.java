package com.chenxianping.blog.service;

import com.chenxianping.blog.entity.BlogLink;
import com.chenxianping.blog.vo.ResultVO;

public interface LinkService {
    //保存友链
    ResultVO save(BlogLink blogLink);

    //审核友链
    ResultVO auditLink(Integer linkId, Integer status);

    ResultVO selectAll(Integer page, Integer pageSize, Integer linkSource, Integer status, String keywords);

    ResultVO selectById(Integer linkId);
}
