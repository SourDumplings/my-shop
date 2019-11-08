package com.cz.my.shop.web.admin.dao;

import com.cz.my.shop.domain.User;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/8 15:44
 */
public interface UserDao
{
    public User getUserByEmailAndPassword(String email, String password);
}
