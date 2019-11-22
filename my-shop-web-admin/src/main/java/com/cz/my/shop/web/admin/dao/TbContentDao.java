package com.cz.my.shop.web.admin.dao;

import com.cz.my.shop.domain.TbContent;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * 内容 Dao.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/22 14:27
 */
@Repository
public interface TbContentDao
{
    /**
     * 查询内容表全部信息
     *
     * @return
     */
    public List<TbContent> selectAll();

    /**
     * 新增
     *
     * @param tbUser
     */
    void insert(TbContent tbUser);

    /**
     * 删除
     *
     * @param id
     */
    void delete(long id);

    /**
     * 根据id获取内容信息
     *
     * @param id
     * @return
     */
    TbContent getById(long id);

    /**
     * 更新内容
     *
     * @param tbUser
     * @return
     */
    void update(TbContent tbUser);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     *
     * @param map start/起始页 length/每页的记录数
     * @return
     */
    List<TbContent> page(Map<String, Object> map);

    /**
     * 查询总记录数
     *
     * @return
     */
    int count(TbContent tbUser);
}
