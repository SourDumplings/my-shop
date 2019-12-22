package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.validator.BeanValidator;
import com.cz.my.shop.domain.TbUser;
import com.cz.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.cz.my.shop.web.admin.dao.TbUserDao;
import com.cz.my.shop.web.admin.service.TbUserService;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/9 8:32
 */
@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser, TbUserDao> implements
    TbUserService
{
    @Override
    public TbUser login(String email, String password)
    {
        TbUser tbUser = dao.getByEmail(email);
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

    @Override
    public BaseResult save(TbUser tbUser)
    {
        String validator = BeanValidator.validator(tbUser);

        if (validator != null)
        {
            // 验证不通过
            return BaseResult.fail(validator);
        }
        else
        {
            // 验证通过
            Date date = new Date();
            tbUser.setUpdated(date);

            // 密码要加密
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));

            if (tbUser.getId() == null)
            {
                // 新增用户
                tbUser.setCreated(date);
                dao.insert(tbUser);
            }
            else
            {
                // 更新用户
                dao.update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }
    }
}
