package com.chenxianping.blog.service;

import com.chenxianping.blog.entity.BlogLink;
import com.chenxianping.blog.vo.ResultVO;

public interface LinkService {
    //新增友链
    ResultVO saveLink(BlogLink blogLink);

    //修改友链
    ResultVO updateLink(BlogLink blogLink);

    //审核友链
    ResultVO checkLink(Integer linkId);

    //下架友链
    ResultVO downLink(Integer linkId);
}
