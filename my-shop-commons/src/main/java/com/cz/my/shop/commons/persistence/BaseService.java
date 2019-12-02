package com.cz.my.shop.commons.persistence;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.dto.PageInfo;
import java.util.List;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/12/2 19:01
 */
public interface BaseService<T extends BaseEntity>
{
    /**
     * 查询数据全部信息
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 新增数据
     *
     * @param element
     */
    void insert(T element);

    /**
     * 删除数据
     *
     * @param id
     */
    void delete(long id);

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    T getById(long id);

    /**
     * 更新数据
     *
     * @param element
     */
    void update(T element);

    /**
     * 保存数据信息
     *
     * @param element
     */
    BaseResult save(T element);

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
    PageInfo<T> page(int start, int length, int draw, T element);

    /**
     * 查询总记录数
     *
     * @return
     */
    int count(T element);
}
