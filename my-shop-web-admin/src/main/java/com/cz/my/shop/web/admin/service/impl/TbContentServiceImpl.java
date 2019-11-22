package com.cz.my.shop.web.admin.service.impl;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.dto.PageInfo;
import com.cz.my.shop.domain.TbContent;
import com.cz.my.shop.web.admin.dao.TbContentDao;
import com.cz.my.shop.web.admin.service.TbContentService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TbContentServiceImpl implements TbContentService
{
    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectAll()
    {
        return tbContentDao.selectAll();
    }

    @Override
    public void delete(long id)
    {
        tbContentDao.delete(id);
    }

    @Override
    public TbContent getById(long id)
    {
        return tbContentDao.getById(id);
    }

    @Override
    public void update(TbContent tbContent)
    {
        tbContentDao.update(tbContent);
    }

    @Override
    public void deleteMulti(String[] ids)
    {
        tbContentDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("length", length);
        map.put("tbContent", tbContent);

        PageInfo<TbContent> res = new PageInfo<>();
        int count = tbContentDao.count(tbContent);
        res.setDraw(draw);
        res.setData(tbContentDao.page(map));
        res.setRecordsTotal(count);
        res.setRecordsFiltered(count);
        res.setError("");
        return res;
    }

    @Override
    public int count(TbContent tbContent)
    {
        return tbContentDao.count(tbContent);
    }

    @Override
    public BaseResult save(TbContent tbContent)
    {
        BaseResult baseResult = checkTbContent(tbContent);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
        {
            Date date = new Date();
            tbContent.setUpdated(date);

            if (tbContent.getId() == null)
            {
                // 新增内容
                tbContent.setCreated(date);
                tbContentDao.insert(tbContent);
            }
            else
            {
                // 更新内容
                tbContentDao.update(tbContent);
            }

            baseResult.setMessage("保存内容信息成功");
        }
        return baseResult;
    }

    /**
     * 内容有效性验证
     *
     * @param tbContent
     * @return
     */
    private BaseResult checkTbContent(TbContent tbContent)
    {
        BaseResult baseResult = BaseResult.success();

        // 非空验证
        if (tbContent.getCategoryId() == null)
        {
            baseResult = BaseResult.fail("分类 id 不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbContent.getTitle()))
        {
            baseResult = BaseResult.fail("标题不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbContent.getSubTitle()))
        {
            baseResult = BaseResult.fail("子标题不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbContent.getTitleDesc()))
        {
            baseResult = BaseResult.fail("标题描述不能为空，请重新输入");
        }
        else if (!StringUtils.isBlank(tbContent.getContent()))
        {
            baseResult = BaseResult.fail("内容不能为空，请重新输入");
        }
        return baseResult;
    }
}
