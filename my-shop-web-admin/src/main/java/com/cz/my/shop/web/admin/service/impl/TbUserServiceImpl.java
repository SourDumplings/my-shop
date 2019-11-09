package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.domain.TbUser;
import com.cz.my.shop.web.admin.dao.TbUserDao;
import com.cz.my.shop.web.admin.service.TbUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/9 8:32
 */
@Service
public class TbUserServiceImpl implements TbUserService
{
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll()
    {
        return tbUserDao.selectAll();
    }

    @Override
    public void insert(TbUser tbUser)
    {
        tbUserDao.insert(tbUser);
    }

    @Override
    public void delete(long id)
    {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser getById(long id)
    {
        return tbUserDao.getById(id);
    }

    @Override
    public void update(TbUser tbUser)
    {
        tbUserDao.update(tbUser);
    }

    @Override
    public List<TbUser> selectByUsername(String username)
    {
        return tbUserDao.selectByUsername(username);
    }

    @Override
    public TbUser login(String email, String password)
    {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if (tbUser != null)
        {
            // 获得md5密码进行查询
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (tbUser.getPassword().equals(md5Password))
            {
                return tbUser;
            }
        }
        return null;
    }
}
