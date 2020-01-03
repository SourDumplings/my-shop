package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.validator.BeanValidator;
import com.cz.my.shop.domain.TbContentCategory;
import com.cz.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.cz.my.shop.web.admin.dao.TbContentCategoryDao;
import com.cz.my.shop.web.admin.service.TbContentCategoryService;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/20 8:28
 */
@Transactional(readOnly = true)
@Service
public class TbContentCategoryServiceImpl extends
    AbstractBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements
    TbContentCategoryService
{
    @Transactional(readOnly = false)
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
                parent.setId(0L);
            }
            tbContentCategory.setUpdated(new Date());

            final Date date = new Date();
            if (tbContentCategory.getId() == null)
            {
                // 新增
                tbContentCategory.setCreated(date);
                tbContentCategory.setIsParent(false);

                // 判断当前新增的节点有没有父级节点
                if (parent.getId() != 0L)
                {
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (currentCategoryParent != null)
                    {
                        // 为父级节点设置 isParent 为 true
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }
                else
                {
                    // 父级节点为 0 ，表示为根目录
                    // 根目录一定是父级目录
                    tbContentCategory.setIsParent(true);
                    tbContentCategory.setParentId(0L);
                }

                tbContentCategory.setUpdated(date);

                insert(tbContentCategory);
            }
            else
            {
                // 修改
                tbContentCategory.setUpdated(date);

                update(tbContentCategory);
            }
            return BaseResult.success("保存分类信息成功");
        }
    }
}
