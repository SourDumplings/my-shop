package com.cz.my.shop.web.api.service.impl;

import com.cz.my.shop.domain.TbContent;
import com.cz.my.shop.domain.TbContentCategory;
import com.cz.my.shop.web.api.dao.TbContentDao;
import com.cz.my.shop.web.api.service.TbContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/1/19 10:52
 */
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService
{
    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId)
    {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);
        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);
        return tbContentDao.selectByCategoryId(tbContent);
    }
}
