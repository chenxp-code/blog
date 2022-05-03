package com.chenxianping.blog.service.impl;

import com.chenxianping.blog.dao.BlogTagMapper;
import com.chenxianping.blog.dao.BlogTagMapperCustom;
import com.chenxianping.blog.entity.BlogTag;
import com.chenxianping.blog.service.TagService;
import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenxp
 * @date 2022/5/2 14:15
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private BlogTagMapper blogTagMapper;

    @Resource
    private BlogTagMapperCustom blogTagMapperCustom;

    /**
     * 保存标签信息
     * tagId为空，则新增标签；tagId不为空，则更新标签
     *
     * @param tag
     * @return
     */
    @Override
    public ResultVO save(BlogTag tag) {
        Date date = new Date();
        ResultVO result;
        //tagName非空校验
        if (tag.getTagName() == null || tag.getTagName().trim().length() == 0) {
            result = new ResultVO(ResStatus.NO, "标签名不能为空！", null);
        } else if (tag.getTagId() == 0 || tag.getTagId() == null) {// tagId为0或null，则为新增
            //删除前后空格
            tag.setTagName(tag.getTagName().trim());
            //tagName唯一性校验
            if (isExist(tag.getTagName())) {
                result = new ResultVO(ResStatus.NO, "标签【" + tag.getTagName() + "】已存在！", null);
            } else {
                //新增标签
                tag.setCreateTime(date);
                tag.setUpdateTime(date);
                tag.setTagAmount(0);
                tag.setDeleted(new Integer(0).byteValue());
                if (blogTagMapper.insert(tag) > 0) {
                    result = new ResultVO(ResStatus.OK, "新增标签【" + tag.getTagName() + "】成功！", tag);
                } else {
                    result = new ResultVO(ResStatus.NO, "新增标签【" + tag.getTagName() + "】失败！", null);
                }
            }
        } else {
            tag.setUpdateTime(date);
            tag.setTagName(tag.getTagName().trim());
            BlogTag tagDb = blogTagMapper.selectByPrimaryKey(tag.getTagId());
            if (tagDb == null) {
                result = new ResultVO(ResStatus.NO, "非法标签Id！", null);
            } else if (!(tag.getTagName()).equals(tagDb.getTagName()) && isExist(tag.getTagName())) {
                result = new ResultVO(ResStatus.NO, "标签【" + tag.getTagName() + "】已存在！", null);
            } else {
                if (blogTagMapper.updateByPrimaryKeySelective(tag) > 0) {
                    result = new ResultVO(ResStatus.OK, "更新标签【" + tag.getTagName() + "】成功！", tag);
                } else {
                    result = new ResultVO(ResStatus.NO, "更新标签【" + tag.getTagName() + "】失败！", null);
                }
            }
        }
        return result;
    }

    /**
     * 根据tagId获取标签信息
     *
     * @param tagId
     * @return
     */
    @Override
    public ResultVO selectById(Integer tagId) {
        BlogTag tag = blogTagMapper.selectByPrimaryKey(tagId);
        ResultVO result;
        if (tag != null) {
            result = new ResultVO(ResStatus.OK, "SUCCESS", tag);
        } else {
            result = new ResultVO(ResStatus.NO, "非法标签ID", null);
        }
        return result;
    }

    /**
     * 获取所有标签(分页)
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public ResultVO selectAll(Integer page, Integer pageSize) {
        Integer offset = (page - 1) * pageSize; //起始下标
        List<BlogTag> tags = blogTagMapperCustom.selectAllForPage(offset, pageSize);
        return new ResultVO(200, "SUCCESS", tags);
    }

    /**
     * 根据tagId删除标签
     * 需判断标签下是否有文章
     *
     * @param tagId
     * @return
     */
    @Override
    public ResultVO deleteById(Integer tagId) {
        BlogTag tag = blogTagMapper.selectByPrimaryKey(tagId);
        ResultVO result;
        if (tag == null) {
            result = new ResultVO(ResStatus.NO, "非法标签ID", null);
        } else if (tag.getTagAmount() <= 0) {
            blogTagMapper.deleteByPrimaryKey(tagId);
            result = new ResultVO(ResStatus.OK, "已删除【" + tag.getTagName() + "】标签", null);
        } else {
            result = new ResultVO(ResStatus.NO, "该标签下有文章，无法删除！", null);
        }
        return result;
    }

    /**
     * 判断tagName是否存在
     *
     * @param tagName
     * @return
     */
    private Boolean isExist(String tagName) {
        //tagName唯一校验
        Example example = new Example(BlogTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tagName", tagName);
        List<BlogTag> tags = blogTagMapper.selectByExample(example);
        return !tags.isEmpty();
    }
}
