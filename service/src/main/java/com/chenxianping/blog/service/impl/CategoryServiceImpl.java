package com.chenxianping.blog.service.impl;

import com.chenxianping.blog.dao.BlogCategoryMapper;
import com.chenxianping.blog.dao.BlogCategoryMapperCustom;
import com.chenxianping.blog.entity.BlogCategory;
import com.chenxianping.blog.service.CategoryService;
import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenxp
 * @date 2022/5/4 14:51
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private BlogCategoryMapper blogCategoryMapper;

    @Resource
    private BlogCategoryMapperCustom blogCategoryMapperCustom;

    /**
     * 保存分类
     * 新增和更新时，需对categoryName进行唯一性校验
     * @param blogCategory
     * @return
     */
    @Override
    public ResultVO save(BlogCategory blogCategory) {
        Date date = new Date();
        ResultVO result;
        if(null == blogCategory){
            result = new ResultVO(ResStatus.NO, "数据错误，请重试！", null);
        }else if (null == blogCategory.getCategoryName() || blogCategory.getCategoryName().trim().length() == 0) {//categoryName非空校验
            result = new ResultVO(ResStatus.NO, "分类名称不能为空！", null);
        } else if (0 == blogCategory.getCategoryId() || null == blogCategory.getCategoryId()) {// categoryId为0或null，则为新增
            //删除前后空格
            blogCategory.setCategoryName(blogCategory.getCategoryName().trim());
            //categoryName唯一性校验
            if (isExist(blogCategory.getCategoryName())) {
                result = new ResultVO(ResStatus.NO, "分类【" + blogCategory.getCategoryName() + "】已存在！", null);
            } else {
                //新增分类
                blogCategory.setCreateTime(date);
                blogCategory.setUpdateTime(date);
                blogCategory.setCategoryAmount(0);
                blogCategory.setDeleted(new Integer(0).byteValue());
                if (blogCategoryMapper.insert(blogCategory) > 0) {
                    result = new ResultVO(ResStatus.OK, "新增分类【" + blogCategory.getCategoryName() + "】成功！", blogCategory);
                } else {
                    result = new ResultVO(ResStatus.NO, "新增分类【" + blogCategory.getCategoryName() + "】失败！", null);
                }
            }
        } else {
            //更新分类
            blogCategory.setUpdateTime(date);
            blogCategory.setCategoryName(blogCategory.getCategoryName().trim());
            BlogCategory categoryDb = blogCategoryMapper.selectByPrimaryKey(blogCategory.getCategoryId());
            if (categoryDb == null) {
                result = new ResultVO(ResStatus.NO, "非法分类Id！", null);
            } else if (!(blogCategory.getCategoryName()).equals(categoryDb.getCategoryName()) && isExist(blogCategory.getCategoryName())) {
                result = new ResultVO(ResStatus.NO, "分类【" + blogCategory.getCategoryName() + "】已存在！", null);
            } else {
                if (blogCategoryMapper.updateByPrimaryKeySelective(blogCategory) > 0) {
                    result = new ResultVO(ResStatus.OK, "更新分类【" + blogCategory.getCategoryName() + "】成功！", blogCategory);
                } else {
                    result = new ResultVO(ResStatus.NO, "更新分类【" + blogCategory.getCategoryName() + "】失败！", null);
                }
            }
        }
        return result;
    }

    /**
     * 根据categoryId获取分类信息
     * @param categoryId
     * @return
     */
    @Override
    public ResultVO selectById(Integer categoryId) {
        BlogCategory category = blogCategoryMapper.selectByPrimaryKey(categoryId);
        ResultVO result;
        if(category != null){
            result = new ResultVO(ResStatus.OK, "SUCCESS", category);
        }else {
            result = new ResultVO(ResStatus.NO, "非法分类Id", null);
        }
        return result;
    }

    /**
     * 根据categoryId删除分类（逻辑删除）
     * @param categoryId
     * @return
     */
    @Override
    public ResultVO deleteById(Integer categoryId) {
        BlogCategory category = blogCategoryMapper.selectByPrimaryKey(categoryId);
        ResultVO result;
        if(category == null ){
            result = new ResultVO(ResStatus.NO, "非法分类Id", null);
        }else if(category.getDeleted() == (byte)1){
            result = new ResultVO(ResStatus.NO, "分类【"+ category.getCategoryName() +"】已被删除", null);
        }else if(category.getCategoryAmount() > 0) {
            result = new ResultVO(ResStatus.NO, "该分类下有文章，无法删除", null);
        }else {
            category.setUpdateTime(new Date());
            category.setDeleted((byte)1);
            int effectRows = blogCategoryMapper.updateByPrimaryKeySelective(category);
            if(effectRows <= 0 ){
                result = new ResultVO(ResStatus.NO, "删除分类【"+ category.getCategoryName() +"】失败", null);
            } else {
                result = new ResultVO(ResStatus.OK, "删除分类【"+ category.getCategoryName() +"】成功", null);
            }
        }
        return result;
    }

    /**
     * 获取所有分类（分页）
     * @param page 页码
     * @param pageSize  每页的记录数
     * @param categoryColumn 分类所属栏目
     * @param keywords 关键词搜索
     * @return
     */
    @Override
    public ResultVO selectAll(Integer page, Integer pageSize, Integer categoryColumn, String keywords) {
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
        List<BlogCategory> categories = blogCategoryMapperCustom.selectAllForPage(offset, pageSize, categoryColumn, keywords);
        return new ResultVO(ResStatus.OK, "SUCCESS", categories);
    }

    /**
     * 根据所属栏目获取分类列表
     * @param column 所属栏目: 1-文章 2-资源库
     * @return
     */
    @Override
    public ResultVO selectByColumn(Integer column) {
        ResultVO result;
        if(column == 1 || column == 2) {
            Example example = new Example(BlogCategory.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("categoryColumn",column);
            criteria.andEqualTo("deleted",0);
            example.orderBy("categorySort").desc();
            List<BlogCategory> categories = blogCategoryMapper.selectByExample(example);
            result = new ResultVO(ResStatus.OK, "SUCCESS", categories);
        }else {
            result = new ResultVO(ResStatus.NO, "非法栏目参数，请重试！", null);
        }

        return result;
    }

    /**
     * 判断categoryName是否存在
     * @param categoryName
     * @return
     */
    private Boolean isExist(String categoryName) {
        //categoryName唯一校验
        Example example = new Example(BlogCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryName", categoryName);
        criteria.andEqualTo("deleted",(byte)0);
        List<BlogCategory> categories = blogCategoryMapper.selectByExample(example);
        return !categories.isEmpty();
    }
}
