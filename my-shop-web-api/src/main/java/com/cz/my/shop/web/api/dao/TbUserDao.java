package com.cz.my.shop.web.api.dao;

import com.cz.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/2/10 9:22
 */
@Repository
public interface TbUserDao
{
    /**
     * 登录
     *
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}