package com.cz.my.shop.web.admin.dao;

import com.cz.my.shop.domain.TbContentCategory;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/20 8:27
 */
@Repository
public interface TbContentCategoryDao
{
    List<TbContentCategory> selectAll();
}
