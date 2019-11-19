package com.cz.my.shop.web.admin.service;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.dto.PageInfo;
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
     * 批量删除
     *
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     *
     * @param start
     * @param length
     * @return
     */
    PageInfo<TbUser> page(int start, int length, int draw, TbUser tbUser);

    /**
     * 查询总记录数
     *
     * @return
     */
    int count(TbUser tbUser);
}
