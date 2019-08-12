package com.firststringboot.service.impl;

import com.firststringboot.common.Exception.CustomException;
import com.firststringboot.common.model.Token;
import com.firststringboot.common.utils.JWTUtil;
import com.firststringboot.repository.domain.User;
import com.firststringboot.repository.mapper.UserMapper;
import com.firststringboot.service.UserService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class UserServiceImpl implements UserService, Serializable {

    private static final long serialVersionUID = 6176157926175957717L;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser() {
     //  PageList<User> users = userMapper.selectObjectListByWhere(new User(),new PageBounds());
        User user = new User();
        user.setName("张三");
        return user;
    }

    @Override
    @Transactional
    public String addUser() throws Exception{
        User user = new User();
        user.setAccount("A10003");
        user.setPassword("123456");
        user.setName("zhangsan");
        user.setPhone("19866986467");
        user.setNickName("ssss");
        user.setSex("男");
        if (userMapper.insertSelective(user) > 0 )throw new CustomException("添加失败！");
        return "success";
    }

    @Override
    public String testToken() {
        Token token = new Token();
        token.setUserId(Long.valueOf(10000));
        String to = JWTUtil.sign(token);
        logger.warn(to);
        return to ;
    }
}
