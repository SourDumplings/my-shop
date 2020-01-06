package com.cz.my.shop.web.admin.web.controller;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.domain.TbContentCategory;
import com.cz.my.shop.web.admin.abstracts.AbstractBaseTreeContrloller;
import com.cz.my.shop.web.admin.service.TbContentCategoryService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
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
public class ContentCategoryController extends
    AbstractBaseTreeContrloller<TbContentCategory, TbContentCategoryService>
{
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
            tbContentCategory = service.getById(id);
        }
        return tbContentCategory;
    }

    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model)
    {
        List<TbContentCategory> targetList = new ArrayList<>();
        final List<TbContentCategory> sourceList = service.selectAll();

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
    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id)
    {
        return service.selectByPid(id);
    }

    /**
     * 跳转到内容分类表单页
     *
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory)
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
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model,
        RedirectAttributes redirectAttributes)
    {
        final BaseResult baseResult = service.save(tbContentCategory);

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

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids)
    {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids))
        {
            service.delete(Long.parseLong(ids));
            baseResult = BaseResult.success("删除分类及其子类及其全部内容成功");
        }
        else
        {
            baseResult = BaseResult.fail("删除分类失败");
        }

        return baseResult;
    }
}
