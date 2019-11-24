package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.domain.TbContentCategory;
import com.cz.my.shop.web.admin.dao.TbContentCategoryDao;
import com.cz.my.shop.web.admin.service.TbContentCategoryService;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public List<TbContentCategory> selectByPid(Long pid)
    {
        return tbContentCategoryDao.selectByPid(pid);
    }

    @Override
    public BaseResult save(TbContentCategory tbContentCategory)
    {
        BaseResult baseResult = checkTbContentCategory(tbContentCategory);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
        {
            Date date = new Date();
            tbContentCategory.setUpdated(date);

            if (tbContentCategory.getId() == null)
            {
                // 新增内容
                tbContentCategory.setCreated(date);
                tbContentCategoryDao.insert(tbContentCategory);
            }
            else
            {
                // 更新内容
                tbContentCategoryDao.update(tbContentCategory);
            }

            baseResult.setMessage("保存内容信息成功");
        }
        return baseResult;
    }

    /**
     * 内容分类有效性验证.
     *
     * @param tbContentCategory
     * @return
     */
    private BaseResult checkTbContentCategory(TbContentCategory tbContentCategory)
    {
        BaseResult baseResult = BaseResult.success();

        // 非空验证
        if (tbContentCategory.getParentId() == null)
        {
            baseResult = BaseResult.fail("父级分类 id 不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbContentCategory.getName()))
        {
            baseResult = BaseResult.fail("分类名不能为空，请重新输入");
        }
        return baseResult;
    }

    @Override
    public TbContentCategory getById(Long id)
    {
        return tbContentCategoryDao.getById(id);
    }
}
