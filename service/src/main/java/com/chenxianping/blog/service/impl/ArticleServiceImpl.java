package com.chenxianping.blog.service.impl;

import com.chenxianping.blog.dao.BlogArticleMapper;
import com.chenxianping.blog.dao.BlogCategoryMapper;
import com.chenxianping.blog.dao.BlogTagMapper;
import com.chenxianping.blog.entity.BlogArticle;
import com.chenxianping.blog.entity.BlogCategory;
import com.chenxianping.blog.entity.BlogTag;
import com.chenxianping.blog.service.ArticleService;
import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    BlogArticleMapper blogArticleMapper;
    BlogTagMapper blogTagMapper;
    BlogCategoryMapper blogCategoryMapper;

    @Override
    public ResultVO saveArticle(BlogArticle blogArticle) {
        //分类
        Integer cid = blogArticle.getCategoryId();
        if(cid==null){
            return new ResultVO(ResStatus.NO,"请选择分类",null);
        }else{
            BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(cid);
            blogCategory.setCategoryAmount(blogCategory.getCategoryAmount()+1);
            blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);
        }
        //标签
        String[] tags = blogArticle.getTagIds().split(",");
        Example example = new Example(BlogTag.class);
        Example.Criteria criteria = example.createCriteria();
        List<BlogTag> newTags = new ArrayList<>();  //需要新增的标签
        List<BlogTag> oldTags = new ArrayList<>();  //已存在的标签
        for (String tag: tags) {
            //检查标签是否存在
            criteria.andEqualTo("tagName",tag);
            List<BlogTag> blogTags = blogTagMapper.selectByExample(example);
            if (blogTags.isEmpty()){    //不存在，则新增
                BlogTag blogTag = new BlogTag();
                blogTag.setTagName(tag);
                blogTag.setCreateTime(new Date());
                newTags.add(blogTag);
            }else {
                oldTags.add(blogTags.get(0));
            }
        }
        //批量新增标签
        blogTagMapper.insertList(newTags);
        //批量建立标签关系

        //保存文章
        blogArticle.setCreateTime(new Date());
        blogArticle.setUpdateTime(new Date());
        blogArticle.setArticlePageView(0);
        blogArticle.setDeleted((byte)0);
        int effectLine = blogArticleMapper.insert(blogArticle);
        if(effectLine>0){
            return new ResultVO(ResStatus.OK,"发布成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"系统错误，稍后再试",null);
        }
    }

    @Override
    public ResultVO updateArticle(BlogArticle blogArticle) {
        BlogArticle oldArticle = blogArticleMapper.selectByPrimaryKey(blogArticle.getArticleId());
        //判断是否修改分类
        if(!oldArticle.getCategoryId().equals(blogArticle.getArticleId())){
            //修改分类

            //还原旧分类的amount

            //给修改后的分类amount加1
        }
        //判断是否修改标签
        if(!oldArticle.getTagIds().equals(blogArticle.getTagIds())){  //修改标签
            //找出需要删除关系的标签
            String[] oldTags = oldArticle.getTagIds().split(",");
            String[] newTags = blogArticle.getTagIds().split(",");
            //将标签id数组转换成List集合
            List<String> oldTagList = new ArrayList<>(oldTags.length);
            Collections.addAll(oldTagList,oldTags);
            List<String> newTagList = new ArrayList<>(newTags.length);
            Collections.addAll(newTagList,newTags);
            List<String> deleteTags = new ArrayList<>();  //放需要删除关系的标签集合
            //遍历旧标签集合找出不在新标签集合中的标签，即为需要删除关系的标签
            for(String tag:oldTagList){
                if(!newTagList.contains(tag)){ //需要删除关系的标签
                    deleteTags.add(tag);
                }else {  //不需要新增关系的旧标签
                    newTagList.remove(tag);
                }
            }
            //删除原来的标签关系

            //新增标签关系
        }
        //保存文章
        blogArticle.setUpdateTime(new Date());
        int effectLine = blogArticleMapper.updateByPrimaryKeySelective(blogArticle);
        if(effectLine>0){
            return new ResultVO(ResStatus.OK,"修改成功",null);
        }else {
            return new ResultVO(ResStatus.NO,"系统错误，稍后再试",null);
        }
    }

    @Override
    public ResultVO deleteArticle(Integer articleId) {
        BlogArticle blogArticle = blogArticleMapper.selectByPrimaryKey(articleId);
        if(blogArticle==null){
            return new ResultVO(ResStatus.NO,"不存在id：" + articleId + "的文章",null);
        }else {
            blogArticle.setDeleted((byte)1);
            blogArticleMapper.updateByPrimaryKeySelective(blogArticle);
            return new ResultVO(ResStatus.OK,"删除成功",null);
        }
    }

    @Override
    public ResultVO deleteArticles(Integer[] articleIds) {
        //blogArticleMapper.deleteBatch(articleIds);
        return null;
    }

    @Override
    public ResultVO getArticleById(Integer articleId) {
        BlogArticle blogArticle = blogArticleMapper.selectByPrimaryKey(articleId);
        if(blogArticle==null){
            return new ResultVO(ResStatus.NO,"不存在id：" + articleId + "的文章",null);
        }else {
            return new ResultVO(ResStatus.OK,"",blogArticle);
        }
    }

    @Override
    public ResultVO getArticlesByKeyword(String keyword) {
        Example example = new Example(BlogArticle.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("articleTitle",keyword);
        List<BlogArticle> blogArticles = blogArticleMapper.selectByExample(example);
        return new ResultVO(ResStatus.OK,"",blogArticles);
    }
}
