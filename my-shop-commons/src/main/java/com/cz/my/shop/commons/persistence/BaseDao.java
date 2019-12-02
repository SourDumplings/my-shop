package com.cz.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/12/2 18:58
 */
public interface BaseDao<T extends BaseEntity>
{
    /**
     * 查询全部数据信息
     *
     * @return
     */
    public List<T> selectAll();

    /**
     * 新增
     *
     * @param element
     */
    void insert(T element);

    /**
     * 删除
     *
     * @param id
     */
    void delete(long id);

    /**
     * 根据id获取数据信息
     *
     * @param id
     * @return
     */
    T getById(long id);


    /**
     * 更新数据
     *
     * @param element
     * @return
     */
    void update(T element);

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
    List<T> page(Map<String, Object> map);

    /**
     * 查询总记录数
     *
     * @return
     */
    int count(T element);
}
