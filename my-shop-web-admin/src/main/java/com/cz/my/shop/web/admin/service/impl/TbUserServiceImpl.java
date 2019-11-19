package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.dto.PageInfo;
import com.cz.my.shop.commons.utils.RegexpUtils;
import com.cz.my.shop.domain.TbUser;
import com.cz.my.shop.web.admin.dao.TbUserDao;
import com.cz.my.shop.web.admin.service.TbUserService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public BaseResult save(TbUser tbUser)
    {
        BaseResult baseResult = checkTbUser(tbUser);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
        {
            Date date = new Date();
            tbUser.setUpdated(date);

            // 密码要加密
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));

            if (tbUser.getId() == null)
            {
                // 新增用户
                tbUser.setCreated(date);
                tbUserDao.insert(tbUser);
            }
            else
            {
                // 更新用户
                tbUserDao.update(tbUser);
            }

            baseResult.setMessage("保存用户信息成功");
        }
        return baseResult;
    }

    /**
     * 用户有效性验证
     *
     * @param tbUser
     * @return
     */
    private BaseResult checkTbUser(TbUser tbUser)
    {
        BaseResult baseResult = BaseResult.success();

        // 非空验证
        if (StringUtils.isBlank(tbUser.getEmail()))
        {
            baseResult = BaseResult.fail("邮箱不能为空，请重新输入");
        }
        else if (!RegexpUtils.checkEmail(tbUser.getEmail()))
        {
            baseResult = BaseResult.fail("邮箱格式不对，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getPassword()))
        {
            baseResult = BaseResult.fail("密码不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getUsername()))
        {
            baseResult = BaseResult.fail("姓名不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getPhone()))
        {
            baseResult = BaseResult.fail("手机号不能为空，请重新输入");
        }
        else if (!RegexpUtils.checkPhone(tbUser.getPhone()))
        {
            baseResult = BaseResult.fail("手机号格式不对，请重新输入");
        }

        return baseResult;
    }

    @Override
    public void deleteMulti(String[] ids)
    {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length, int draw, TbUser tbUser)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("length", length);
        map.put("tbUser", tbUser);

        PageInfo<TbUser> res = new PageInfo<>();
        int count = tbUserDao.count(tbUser);
        res.setDraw(draw);
        res.setData(tbUserDao.page(map));
        res.setRecordsTotal(count);
        res.setRecordsFiltered(count);
        res.setError("");
        return res;
    }

    @Override
    public int count(TbUser tbUser)
    {
        return tbUserDao.count(tbUser);
    }
}
