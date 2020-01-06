package com.cz.my.shop.web.admin.abstracts;

import com.cz.my.shop.commons.persistence.BaseEntity;
import com.cz.my.shop.commons.persistence.BaseTreeDao;
import com.cz.my.shop.commons.persistence.BaseTreeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/12/22 15:04
 */
public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements
    BaseTreeService<T>
{
    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll()
    {
        return dao.selectAll();
    }

    @Override
    public void delete(Long id)
    {
        dao.delete(new String[]{String.valueOf(id)});
    }

    @Override
    public T getById(Long id)
    {
        return dao.getById(id);
    }

    @Override
    public void update(T entity)
    {
        dao.update(entity);
    }

    @Override
    public List<T> selectByPid(Long pid)
    {
        return dao.selectByPid(pid);
    }

    public void insert(T entity)
    {
        dao.insert(entity);
    }
}
