package com.cz.my.shop.web.admin.abstracts;

import com.cz.my.shop.commons.dto.PageInfo;
import com.cz.my.shop.commons.persistence.BaseDao;
import com.cz.my.shop.commons.persistence.BaseEntity;
import com.cz.my.shop.commons.persistence.BaseService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/12/22 15:25
 */
public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements
    BaseService<T>
{
    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll()
    {
        return dao.selectAll();
    }

    @Override
    public void insert(T element)
    {
        dao.insert(element);
    }

    @Override
    public void delete(long id)
    {
        dao.delete(id);
    }

    @Override
    public T getById(long id)
    {
        return dao.getById(id);
    }

    @Override
    public void update(T element)
    {
        dao.update(element);
    }

    @Override
    public void deleteMulti(String[] ids)
    {
        dao.deleteMulti(ids);
    }

    @Override
    public int count(T element)
    {
        return dao.count(element);
    }

    /**
     * 分页查询
     *
     * @param start
     * @param length
     * @param draw
     * @param entity
     * @return
     */
    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity)
    {
        int count = count(entity);

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("pageParams", entity);

        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(params));

        return pageInfo;
    }
}
