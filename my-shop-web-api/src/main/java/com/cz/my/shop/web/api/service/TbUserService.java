package com.cz.my.shop.web.api.service;

import com.cz.my.shop.domain.TbUser;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/2/10 9:36
 */
public interface TbUserService
{

    /**
     * 登录
     *
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
