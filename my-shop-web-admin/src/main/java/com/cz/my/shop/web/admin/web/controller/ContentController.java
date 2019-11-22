package com.cz.my.shop.web.admin.web.controller;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.dto.PageInfo;
import com.cz.my.shop.domain.TbContent;
import com.cz.my.shop.web.admin.service.TbContentService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 内容 Controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/22 15:27
 */
@RequestMapping("content")
@Controller
public class ContentController
{
    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id)
    {
        TbContent tbContent = null;
        if (id == null)
        {
            tbContent = new TbContent();
        }
        else
        {
            tbContent = tbContentService.getById(id);
        }
        return tbContent;
    }

    /**
     * 跳转到内容列表页
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list()
    {
        return "content_list";
    }

    /**
     * 跳转到内容表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form()
    {
        return "content_form";
    }

    /**
     * 保存内容信息
     *
     * @param tbContent
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes)
    {
        final BaseResult baseResult = tbContentService.save(tbContent);

        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
        {
            // 保存成功
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        }
        else
        {
            // 保存失败
            model.addAttribute("baseResult", baseResult);
            return "content_form";
        }
    }

    /**
     * 删除内容信息
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids)
    {
        BaseResult baseResult = null;

        if (StringUtils.isNotBlank(ids))
        {
            baseResult = BaseResult.success();
            baseResult.setMessage("删除成功");
            String[] idArray = ids.trim().split(",");
            tbContentService.deleteMulti(idArray);
        }
        else
        {
            baseResult = BaseResult.fail();
            baseResult.setMessage("删除失败");

        }
        return baseResult;
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent)
    {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        // 封装 DataTables 需要的结果
        return tbContentService.page(start, length, draw, tbContent);
    }

    /**
     * 显示内容详情
     *
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbContent tbContent)
    {
        return "content_detail";
    }
}
