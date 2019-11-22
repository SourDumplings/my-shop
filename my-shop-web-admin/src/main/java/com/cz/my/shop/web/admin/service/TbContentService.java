package com.cz.my.shop.web.admin.service;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.dto.PageInfo;
import com.cz.my.shop.domain.TbContent;
import java.util.List;
import java.util.Map;

/**
 * 内容 Service.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/22 14:28
 */
public interface TbContentService
{
    /**
     * 查询内容表全部信息
     *
     * @return
     */
    public List<TbContent> selectAll();

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
     * @param start
     * @param length
     * @return
     */
    PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    /**
     * 查询总记录数
     *
     * @return
     */
    int count(TbContent tbUser);

    /**
     * 保存内容信息
     *
     * @param tbContent
     */
    BaseResult save(TbContent tbContent);
}
