package com.cz.my.shop.web.admin.service;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.domain.TbUser;
import java.util.List;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/9 8:31
 */
public interface TbUserService
{
    /**
     * 查询用户全部信息
     *
     * @return
     */
    List<TbUser> selectAll();

    /**
     * 新增用户
     *
     * @param tbUser
     */
    void insert(TbUser tbUser);

    /**
     * 删除用户
     *
     * @param id
     */
    void delete(long id);

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    TbUser getById(long id);

    /**
     * 更新用户
     *
     * @param tbUser
     */
    void update(TbUser tbUser);

    /**
     * 使用用户名模糊查询
     *
     * @param username
     * @return
     */
    List<TbUser> selectByUsername(String username);

    /**
     * 用户通过邮箱登录
     *
     * @param email
     * @return
     */
    TbUser login(String email, String password);

    /**
     * 保存用户信息
     *
     * @param tbUser
     */
    BaseResult save(TbUser tbUser);

    /**
     * 搜索
     *
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);
}
