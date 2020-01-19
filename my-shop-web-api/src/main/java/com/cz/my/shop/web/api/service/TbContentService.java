package com.cz.my.shop.web.api.service;

import com.cz.my.shop.domain.TbContent;
import java.util.List;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/1/19 10:51
 */
public interface TbContentService
{
    /**
     * 根据类别 ID 查询内容列表.
     *
     * @param categoryId
     * @return
     */
    List<TbContent> selectByCategoryId(Long categoryId);
}
