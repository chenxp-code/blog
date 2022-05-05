package com.chenxianping.blog.service.impl;

import com.chenxianping.blog.dao.BlogResourceMapper;
import com.chenxianping.blog.dao.BlogResourceMapperCustom;
import com.chenxianping.blog.entity.BlogResource;
import com.chenxianping.blog.service.ResourceService;
import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenxp
 * @date 2022/5/5 22:14
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private BlogResourceMapper blogResourceMapper;

    @Resource
    private BlogResourceMapperCustom blogResourceMapperCustom;

    /**
     * 保存资源
     * resourceId为空时，则新增；不为空时，则更新
     * @param resource
     * @return
     */
    @Override
    public ResultVO save(BlogResource resource) {
        ResultVO result;
        Date date = new Date();
        if (resource == null) {
            result = new ResultVO(ResStatus.NO, "数据为空，请重试", null);
        } else if (0 == resource.getResourceId() || null == resource.getResourceId()) {
            //新增资源
            resource.setCreateTime(date);
            resource.setUpdateTime(date);
            if (blogResourceMapper.insert(resource) > 0) {
                result = new ResultVO(ResStatus.OK, "新增资源【" + resource.getResourceName() + "】成功", resource);
            }else {
                result = new ResultVO(ResStatus.NO, "新增资源【" + resource.getResourceName() + "】失败", null);
            }
        }else {
            resource.setUpdateTime(date);
            if(blogResourceMapper.updateByPrimaryKeySelective(resource) > 0) {
                result = new ResultVO(ResStatus.OK, "更新资源【" + resource.getResourceName() + "】成功", resource);
            }else {
                result = new ResultVO(ResStatus.NO, "更新资源【" + resource.getResourceName() + "】失败", null);
            }
        }
        return result;
    }

    /**
     * 根据resourceId获取资源信息
     * @param resourceId 资源Id
     * @return
     */
    @Override
    public ResultVO selectById(Integer resourceId) {
        BlogResource resource = blogResourceMapper.selectByPrimaryKey(resourceId);
        ResultVO result;
        if (null != resource) {
            result = new ResultVO(ResStatus.OK, "SUCCESS", resource);
        } else {
            result = new ResultVO(ResStatus.NO, "非法资源Id", null);
        }
        return result;
    }

    /**
     * 获取所有资源(分页)
     *
     * @param page     页码
     * @param pageSize 每页的记录数
     * @param categoryId 分类Id
     * @param keywords 关键词
     * @return
     */
    @Override
    public ResultVO selectAll(Integer page, Integer pageSize, Integer categoryId, String keywords) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (pageSize == null || pageSize < 10) {
            pageSize = 10;
        }
        if (pageSize > 50) {
            pageSize = 50;
        }
        if (null != keywords) {
            keywords = keywords.trim();
            if (keywords.length() > 0) {
                keywords = "%" + keywords + "%";
            } else {
                keywords = "";
            }
        }

        Integer offset = (page - 1) * pageSize; //起始下标
        List<BlogResource> resources = blogResourceMapperCustom.selectAllForPage(offset, pageSize, categoryId, keywords);
        return new ResultVO(ResStatus.OK, "SUCCESS", resources);
    }

    /**
     * 根据resourceId删除资源
     *
     * @param resourceId 资源Id
     * @return
     */
    @Override
    public ResultVO deleteById(Integer resourceId) {
        ResultVO result;
        BlogResource resource = blogResourceMapper.selectByPrimaryKey(resourceId);
        if (resource == null) {
            result = new ResultVO(ResStatus.NO, "非法资源Id", null);
        } else {
            blogResourceMapper.deleteByPrimaryKey(resourceId);
            result = new ResultVO(ResStatus.OK, "删除资源【" + resource.getResourceName() + "】成功", null);
        }
        return result;
    }
}
