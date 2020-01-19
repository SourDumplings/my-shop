package com.cz.my.shop.web.api.dao;

import com.cz.my.shop.domain.TbContent;
import java.util.List;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/1/19 10:39
 */
public interface TbContentDao
{
    /**
     * 根据类别 ID 查询内容列表.
     *
     * @param tbContent
     * @return
     */
    List<TbContent> selectByCategoryId(TbContent tbContent);
}
