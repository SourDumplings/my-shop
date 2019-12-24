package com.cz.my.shop.web.admin.abstracts;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.persistence.BaseTreeEntity;
import com.cz.my.shop.commons.persistence.BaseTreeService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/12/24 22:00
 */
public abstract class AbstractBaseTreeContrloller<T extends BaseTreeEntity, S extends BaseTreeService<T>>
{
    @Autowired
    protected S service;

    /**
     * 跳转列表页
     *
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**
     * 跳转表单页
     *
     * @return
     */
    public abstract String form(T entity);

    /**
     * 保存
     *
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);

    /**
     * 树形结构
     *
     * @param id
     * @return
     */
    public abstract List<T> treeData(Long id);

    /**
     * 排序.
     *
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId   父结点的id
     */
    protected void sortList(List<T> sourceList, List<T> targetList,
        Long parentId, Set<T> added)
    {
        for (T entity : sourceList)
        {
            if (!added.contains(entity) && entity.getParent().getId()
                .equals(parentId))
            {
                targetList.add(entity);
                added.add(entity);

                if (entity.getIsParent())
                {
                    // 如果有子结点则递归继续追加
                    sortList(sourceList, targetList, entity.getId(), added);
                }
            }
        }
    }
}
