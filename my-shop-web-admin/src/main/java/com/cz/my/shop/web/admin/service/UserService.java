package com.cz.my.shop.web.admin.service;

import com.cz.my.shop.domain.User;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/8 15:50
 */
public interface UserService
{
    /**
     * 登录
     *
     * @param email
     * @param password
     * @return
     */
    public User login(String email, String password);

}
