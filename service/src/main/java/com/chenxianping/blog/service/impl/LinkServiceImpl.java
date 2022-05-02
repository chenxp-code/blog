package com.chenxianping.blog.service.impl;

import com.chenxianping.blog.dao.BlogLinkMapper;
import com.chenxianping.blog.entity.BlogLink;
import com.chenxianping.blog.service.LinkService;
import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class LinkServiceImpl implements LinkService {
    @Autowired
    BlogLinkMapper blogLinkMapper;

    @Override
    public ResultVO saveLink(BlogLink blogLink) {
        //网站图片为空则设为默认随机图片
        if(blogLink.getLinkIcon()==null){
            blogLink.setLinkIcon("siteicon.png");
        }
        //自主申请的需要审核，管理员添加的无需审核
        if(blogLink.getLinkSource()==1){
            blogLink.setLinkStatus((byte)0);
        }else {
            blogLink.setLinkStatus((byte)1);
        }
        //保存友链信息
        blogLink.setCreateTime(new Date());
        blogLink.setUpdateTime(new Date());
        int effectLine = blogLinkMapper.insert(blogLink);
        if(effectLine>0){
            return new ResultVO(ResStatus.OK,"已提交",null);
        }else {
            return new ResultVO(ResStatus.NO,"系统错误，稍后再试",null);
        }
    }

    @Override
    public ResultVO updateLink(BlogLink blogLink) {
        blogLink.setUpdateTime(new Date());
        int effectLine = blogLinkMapper.updateByPrimaryKeySelective(blogLink);
        if(effectLine>0){
            return new ResultVO(ResStatus.OK,"修改成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"系统错误，稍后再试",null);
        }
    }

    @Override
    public ResultVO checkLink(Integer linkId) {
        BlogLink blogLink = blogLinkMapper.selectByPrimaryKey(linkId);
        blogLink.setLinkStatus((byte)1);
        int effectLine = blogLinkMapper.updateByPrimaryKeySelective(blogLink);
        if(effectLine>0){
            return new ResultVO(ResStatus.OK,"审核完成",null);
        }else {
            return new ResultVO(ResStatus.NO,"系统错误，稍后再试",null);
        }
    }

    @Override
    public ResultVO downLink(Integer linkId) {
        BlogLink blogLink = blogLinkMapper.selectByPrimaryKey(linkId);
        blogLink.setLinkStatus((byte)2);
        int effectLine = blogLinkMapper.updateByPrimaryKeySelective(blogLink);
        if(effectLine>0){
            return new ResultVO(ResStatus.OK,"下线完成",null);
        }else {
            return new ResultVO(ResStatus.NO,"系统错误，稍后再试",null);
        }
    }
}
