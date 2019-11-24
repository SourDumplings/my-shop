package com.cz.my.shop.web.admin.web.controller;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.domain.TbContentCategory;
import com.cz.my.shop.web.admin.service.TbContentCategoryService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 内容分类管理
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/20 8:29
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController
{
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id)
    {
        TbContentCategory tbContentCategory = null;
        if (id == null)
        {
            tbContentCategory = new TbContentCategory();
        }
        else
        {
            tbContentCategory = tbContentCategoryService.getById(id);
        }
        return tbContentCategory;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model)
    {
        List<TbContentCategory> targetList = new ArrayList<>();
        final List<TbContentCategory> sourceList = tbContentCategoryService.selectAll();

        Set<TbContentCategory> added = new HashSet<>();
        sortList(sourceList, targetList, 0L, added);

        model.addAttribute("tbContentCategories", targetList);

        return "content_category_list";
    }

    /**
     * 树形结构.
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(String id)
    {
        if (id == null)
        {
            id = "0";
        }
        return tbContentCategoryService
            .selectByPid(Long.parseLong(id));
    }

    /**
     * 排序.
     *
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId   父结点的id
     */
    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList,
        Long parentId, Set<TbContentCategory> added)
    {
        for (TbContentCategory tbContentCategory : sourceList)
        {
            if (!added.contains(tbContentCategory) && tbContentCategory.getParentId()
                .equals(parentId))
            {
                targetList.add(tbContentCategory);
                added.add(tbContentCategory);

                if (tbContentCategory.getIsParent())
                {
                    // 如果有子结点则递归继续追加
                    sortList(sourceList, targetList, tbContentCategory.getId(), added);
                }
            }
        }
    }

    /**
     * 跳转到内容分类表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form()
    {
        return "content_category_form";
    }

    /**
     * 保存内容分类信息
     *
     * @param tbContentCategory
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model,
        RedirectAttributes redirectAttributes)
    {
        final BaseResult baseResult = tbContentCategoryService.save(tbContentCategory);

        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
        {
            // 保存成功
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        }
        else
        {
            // 保存失败
            model.addAttribute("baseResult", baseResult);
            return "content_category_form";
        }
    }
}
