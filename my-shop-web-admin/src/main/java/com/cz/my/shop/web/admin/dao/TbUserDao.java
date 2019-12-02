package com.cz.my.shop.web.admin.dao;

import com.cz.my.shop.commons.persistence.BaseDao;
import com.cz.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/9 8:30
 */
@Repository
public interface TbUserDao extends BaseDao<TbUser>
{
    /**
     * 通过邮箱获取用户信息
     *
     * @param email
     * @return
     */
    TbUser getByEmail(String email);
}
