package com.cz.my.shop.web.admin.dao.impl;

import com.cz.my.shop.domain.User;
import com.cz.my.shop.web.admin.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/8 15:45
 */
@Repository(value = "userDao")
public class UserDaoImpl implements UserDao
{
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User getUserByEmailAndPassword(String email, String password)
    {
        logger.info("用户开始尝试登录。。。");
        User user = null;
        if ("admin@cz.com".equals(email))
        {
            if ("admin".equals(password))
            {
                user = new User();
                user.setUsername("admin");
                user.setEmail(email);
                logger.info("{} 登录成功", user.getUsername());
            }
        }
        return user;
    }
}
