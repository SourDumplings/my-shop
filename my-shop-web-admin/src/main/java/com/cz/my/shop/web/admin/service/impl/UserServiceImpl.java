package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.domain.User;
import com.cz.my.shop.web.admin.dao.UserDao;
import com.cz.my.shop.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/8 15:52
 */
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;

    public User login(String email, String password)
    {
        return userDao.getUserByEmailAndPassword(email, password);
    }
}