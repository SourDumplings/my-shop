package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.dto.PageInfo;
import com.cz.my.shop.commons.validator.BeanValidator;
import com.cz.my.shop.domain.TbContentCategory;
import com.cz.my.shop.web.admin.dao.TbContentCategoryDao;
import com.cz.my.shop.web.admin.service.TbContentCategoryService;
import java.util.Date;
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
    public void insert(TbContentCategory element)
    {

    }

    @Override
    public void delete(long id)
    {

    }

    @Override
    public void update(TbContentCategory element)
    {

    }

    @Override
    public void deleteMulti(String[] ids)
    {

    }

    @Override
    public PageInfo<TbContentCategory> page(int start, int length, int draw,
        TbContentCategory element)
    {
        return null;
    }

    @Override
    public int count(TbContentCategory element)
    {
        return 0;
    }

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
        String validator = BeanValidator.validator(tbContentCategory);
        if (validator != null)
        {
            return BaseResult.fail(validator);
        }
        else
        {
            TbContentCategory parent = tbContentCategory.getParent();
            if (parent == null || parent.getId() == null)
            {
                // 如果没有选择父级节点则默认设置为根目录
                // 0 代表根目录
                tbContentCategory.setParentId(0L);
            }
            tbContentCategory.setUpdated(new Date());

            if (tbContentCategory.getId() == null)
            {
                // 新增
                tbContentCategory.setCreated(new Date());
                tbContentCategory.setIsParent(false);

                // 判断当前新增的节点有没有父级节点
                if (tbContentCategory.getParentId() != 0L)
                {
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (currentCategoryParent != null)
                    {
                        // 为父级节点设置 isParent 为 true
                        currentCategoryParent.setIsParent(true);
                        tbContentCategoryDao.update(currentCategoryParent);
                    }
                }
                else
                {
                    // 父级节点为 0 ，表示为根目录
                    // 根目录一定是父级目录
                    tbContentCategory.setIsParent(true);
                }

                tbContentCategoryDao.insert(tbContentCategory);
            }

            else
            {
                // 修改
                tbContentCategoryDao.update(tbContentCategory);
            }
            return BaseResult.success("保存分类信息成功");
        }
    }

    @Override
    public TbContentCategory getById(long id)
    {
        return tbContentCategoryDao.getById(id);
    }
}
