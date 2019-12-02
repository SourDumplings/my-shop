package com.cz.my.shop.web.admin.service;

import com.cz.my.shop.commons.persistence.BaseService;
import com.cz.my.shop.domain.TbUser;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/9 8:31
 */
public interface TbUserService extends BaseService<TbUser>
{
    /**
     * 用户通过邮箱登录
     *
     * @param email
     * @return
     */
    TbUser login(String email, String password);

}
