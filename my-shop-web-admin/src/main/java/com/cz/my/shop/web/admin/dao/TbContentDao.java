package com.cz.my.shop.web.admin.dao;

import com.cz.my.shop.commons.persistence.BaseDao;
import com.cz.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

/**
 * 内容 Dao.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/22 14:27
 */
@Repository
public interface TbContentDao extends BaseDao<TbContent>
{
    /**
     * 根据类目 ID 删除内容
     *
     * @param categoryIds
     */
    void deleteByCategoryId(String[] categoryIds);
}
