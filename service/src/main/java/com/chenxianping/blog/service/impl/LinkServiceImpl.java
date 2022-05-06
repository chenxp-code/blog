package com.chenxianping.blog.service.impl;

import com.chenxianping.blog.dao.BlogLinkMapper;
import com.chenxianping.blog.dao.BlogLinkMapperCustom;
import com.chenxianping.blog.entity.BlogLink;
import com.chenxianping.blog.service.LinkService;
import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    @Resource
    private BlogLinkMapper blogLinkMapper;

    @Resource
    private BlogLinkMapperCustom blogLinkMapperCustom;

    @Override
    public ResultVO save(BlogLink blogLink) {
        ResultVO result;
        Date date = new Date();
        if(null == blogLink) {
            result = new ResultVO(ResStatus.NO,"数据错误，请重试",null);
        }else if(0 == blogLink.getLinkId() || null == blogLink.getLinkId()) {
            // 新增友链
            // 网站图片为空则设为默认随机图片
            if(null == blogLink.getLinkIcon() || "".equals(blogLink.getLinkIcon())){
                blogLink.setLinkIcon("siteIcon.png");
            }

            blogLink.setLinkStatus((byte)0);
            blogLink.setLinkSource((byte)2);
            blogLink.setCreateTime(date);
            blogLink.setUpdateTime(date);
            int effectLine = blogLinkMapper.insert(blogLink);
            if(effectLine>0){
                result = new ResultVO(ResStatus.OK,"添加友链【" + blogLink.getLinkName() + "】成功",null);
            }else {
                result = new ResultVO(ResStatus.NO,"添加友链【" + blogLink.getLinkName() + "】失败",null);
            }
        }else {
            //更新友链
            blogLink.setUpdateTime(date);
            int effectLine = blogLinkMapper.updateByPrimaryKeySelective(blogLink);
            if(effectLine>0){
                result = new ResultVO(ResStatus.OK,"更新友链【" + blogLink.getLinkName() + "】成功",null);
            }else {
                result = new ResultVO(ResStatus.NO,"更新友链【" + blogLink.getLinkName() + "】失败",null);
            }
        }
        return result;
    }

    /**
     * 审核友链状态
     * @param linkId 友链Id
     * @param status 友链状态：0-待审核 1-已通过 2-未通过 3-已下线
     * @return
     */
    @Override
    public ResultVO auditLink(Integer linkId, Integer status) {
        ResultVO result;
        BlogLink blogLink = blogLinkMapper.selectByPrimaryKey(linkId);
        if(null == blogLink){
            result = new ResultVO(ResStatus.NO,"非法友链Id",null);
        }else {
            blogLink.setLinkStatus(status.byteValue());
            blogLink.setUpdateTime(new Date());
            int effectLine = blogLinkMapper.updateByPrimaryKeySelective(blogLink);
            if(effectLine>0){
                result = new ResultVO(ResStatus.OK,"审核友链成功",null);
            }else {
                result = new ResultVO(ResStatus.NO,"操作失败，稍后再试",null);
            }
        }
        return result;
    }

    /**
     * 获取所有分类（分页）
     * @param page 页码
     * @param pageSize 每页的记录数
     * @param linkSource 友链来源
     * @param status 友链状态
     * @param keywords 关键词
     * @return
     */
    @Override
    public ResultVO selectAll(Integer page, Integer pageSize, Integer linkSource, Integer status, String keywords) {
        if(page == null || page < 1){
            page = 1;
        }
        if(pageSize == null || pageSize < 10){
            pageSize = 10;
        }
        if(pageSize > 50 ){
            pageSize = 50;
        }
        if(null != keywords){
            keywords = keywords.trim();
            if(keywords.length() > 0){
                keywords = "%" + keywords + "%";
            }else {
                keywords = "";
            }
        }

        Integer offset = (page - 1) * pageSize; //起始下标
        List<BlogLink> links = blogLinkMapperCustom.selectAllForPage(offset, pageSize, linkSource, status, keywords);
        return new ResultVO(ResStatus.OK, "SUCCESS", links);
    }

    /**
     * 根据linkId获取友链
     * @param linkId 友链Id
     * @return
     */
    @Override
    public ResultVO selectById(Integer linkId) {
        BlogLink blogLink = blogLinkMapper.selectByPrimaryKey(linkId);
        ResultVO result;
        if(null == blogLink) {
            result = new ResultVO(ResStatus.NO,"非法友链Id",null);
        }else {
            result = new ResultVO(ResStatus.OK,"SUCCESS",blogLink);
        }
        return result;
    }
}
