package com.cz.my.shop.web.admin.service;

import com.cz.my.shop.commons.persistence.BaseService;
import com.cz.my.shop.domain.TbContentCategory;
import java.util.List;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/20 8:28
 */
public interface TbContentCategoryService extends BaseService<TbContentCategory>
{
    /**
     * 根据父级节点查询所有子节点.
     *
     * @param pid
     * @return
     */
    List<TbContentCategory> selectByPid(Long pid);
}
