package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.domain.TbContentCategory;
import com.cz.my.shop.web.admin.dao.TbContentCategoryDao;
import com.cz.my.shop.web.admin.service.TbContentCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/20 8:28
 */
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService
{
    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> selectAll()
    {
        return tbContentCategoryDao.selectAll();
    }
}
