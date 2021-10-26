package com.chenxianping.blog.service.impl;

import com.chenxianping.blog.dao.BlogAdminMapper;
import com.chenxianping.blog.entity.BlogAdmin;
import com.chenxianping.blog.service.UserService;
import com.chenxianping.blog.utils.MD5Utils;
import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BlogAdminMapper blogAdminMapper;

    @Override
    public ResultVO addAdmin(String userName, String userPassword,Byte userType) {
        //1.先判断账号是否存在
        Example example = new Example(BlogAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",userName);
        List<BlogAdmin> admins = blogAdminMapper.selectByExample(example);

        if(admins.isEmpty()){
            //2.MD5加密管理员密码
            BlogAdmin admin = new BlogAdmin();
            admin.setUserName(userName);
            String md5Pwd = MD5Utils.md5(userPassword);
            admin.setUserPassword(md5Pwd);
            admin.setUserType(userType);
            admin.setUserNickname("小陈");
            admin.setUserEmail("1441173216@qq.com");
            Byte b = 1;
            admin.setUserStatus(b);
            admin.setCreateTime(new Date());
            admin.setUpdateTime(new Date());

            //3.存入数据库
            int insert = blogAdminMapper.insert(admin);
            if (insert>0){
                return new ResultVO(ResStatus.OK,"添加成功",null);
            }else {
                return new ResultVO(ResStatus.NO,"添加失败：系统错误",null);
            }
        }

        return new ResultVO(ResStatus.NO,"添加失败：用户名已存在",null);
    }

    @Override
    public ResultVO loginAdmin(String userName, String userPassword) {
        //1.先判断账号是否存在
        Example example = new Example(BlogAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",userName);
        List<BlogAdmin> admins = blogAdminMapper.selectByExample(example);
        if (admins.isEmpty()){
            return new ResultVO(ResStatus.NO,"登陆失败：账号不存在",null);
        }
        //2.判断密码是否正确
        if (MD5Utils.md5(userPassword).equals(admins.get(0).getUserPassword())){
            //如果登录验证成功，则需要生成令牌token（token就是按照特定规则生成的字符串）
            //使用jwt规则生成token字符串
            JwtBuilder builder = Jwts.builder();

            HashMap<String,Object> map = new HashMap<>();
            map.put("key1","value1");
            map.put("key2","value2");

            String token = builder.setSubject(userName)               //主题，就是token中携带的数据
                    .setIssuedAt(new Date())                          //设置token的生成时间
                    .setId(admins.get(0).getId() + "")                //设置用户id为token id
                    .setClaims(map)                                     //map中可以存放用户的角色权限信息
                    .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000)) //设置token过期时间
                    .signWith(SignatureAlgorithm.HS256, "firstBlog")     //设置加密方式和加密密码
                    .compact();

            return new ResultVO(ResStatus.OK,token,admins.get(0));
        }else {
            return new ResultVO(ResStatus.NO,"登陆失败：密码错误",null);
        }
    }

    @Override
    public ResultVO updatePassword(Integer id, String userPassword, String newPassword) {
        BlogAdmin admin = blogAdminMapper.selectByPrimaryKey(id);
        //判断原密码是否正确
        if (MD5Utils.md5(userPassword).equals(admin.getUserPassword())){
            //更新管理员密码
            admin.setUserPassword(MD5Utils.md5(newPassword));
            admin.setUpdateTime(new Date());
            int i = blogAdminMapper.updateByPrimaryKeySelective(admin);
            if (i>0){
                return new ResultVO(ResStatus.OK,"修改成功",null);
            }else {
                return new ResultVO(ResStatus.NO,"修改失败：系统错误",null);
            }
        }else {
            return new ResultVO(ResStatus.NO,"修改失败：原密码错误",null);
        }
    }
}
