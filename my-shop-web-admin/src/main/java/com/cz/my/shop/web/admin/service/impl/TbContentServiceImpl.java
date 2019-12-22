package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.validator.BeanValidator;
import com.cz.my.shop.domain.TbContent;
import com.cz.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.cz.my.shop.web.admin.dao.TbContentDao;
import com.cz.my.shop.web.admin.service.TbContentService;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * 内容 Service 实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/22 14:28
 */
@Service
public class TbContentServiceImpl extends
    AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService
{
    @Override
    public BaseResult save(TbContent tbContent)
    {
        String validator = BeanValidator.validator(tbContent);

        if (validator != null)
        {
            // 验证不通过

            return BaseResult.fail(validator);
        }
        else
        {
            // 验证通过

            tbContent.setUpdated(new Date());

            if (tbContent.getId() == null)
            {
                // 新增
                // 密码需要加密处理
                tbContent.setCreated(new Date());
                insert(tbContent);
            }
            else
            {
                // 编辑用户
                update(tbContent);
            }

            return BaseResult.success("保存内容信息成功");
        }
    }
}
