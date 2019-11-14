package com.cz.my.shop.web.admin.dao;

import com.cz.my.shop.domain.TbUser;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/9 8:30
 */
@Repository
public interface TbUserDao
{
    /**
     * 查询用户表全部信息
     *
     * @return
     */
    public List<TbUser> selectAll();

    /**
     * 新增
     *
     * @param tbUser
     */
    void insert(TbUser tbUser);

    /**
     * 删除
     *
     * @param id
     */
    void delete(long id);

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    TbUser getById(long id);

    /**
     * 通过邮箱获取用户信息
     *
     * @param email
     * @return
     */
    TbUser getByEmail(String email);

    /**
     * 更新用户
     *
     * @param tbUser
     * @return
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
     * 搜索
     *
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);
}
