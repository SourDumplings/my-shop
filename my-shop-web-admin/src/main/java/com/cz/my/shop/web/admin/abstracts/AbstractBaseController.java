package com.cz.my.shop.web.admin.abstracts;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.dto.PageInfo;
import com.cz.my.shop.commons.persistence.BaseEntity;
import com.cz.my.shop.commons.persistence.BaseService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/12/24 21:40
 */
public abstract class AbstractBaseController<T extends BaseEntity, S extends BaseService<T>>
{
    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

    /**
     * 跳转列表页
     *
     * @return
     */
    public abstract String list();

    /**
     * 跳转到表单页
     *
     * @return
     */
    public abstract String form();

    /**
     * 保存信息
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
     * 分页查询
     *
     * @param request
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<T> page(HttpServletRequest request, T entity)
    {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        // 封装 Datatables 需要的结果
        PageInfo<T> pageInfo = service.page(start, length, draw, entity);

        return pageInfo;
    }

    /**
     * 跳转详情页
     *
     * @return
     */
    public abstract String detail();
}
