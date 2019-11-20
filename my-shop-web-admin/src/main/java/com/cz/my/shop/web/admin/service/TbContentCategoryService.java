package com.cz.my.shop.web.admin.service;

import com.cz.my.shop.domain.TbContentCategory;
import java.util.List;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/20 8:28
 */
public interface TbContentCategoryService
{
    List<TbContentCategory> selectAll();
}
