package com.cz.my.shop.web.admin.web.interceptor;

import com.cz.my.shop.commons.constant.ConstantUtils;
import com.cz.my.shop.domain.TbUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 权限拦截器：如果用户已登录的情况下访问登录页则会跳转至首页。
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/6 10:27
 */
public class PermissionInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Object o) throws Exception
    {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
        throws Exception
    {
        // 以login结尾的请求
        if (modelAndView != null && modelAndView.getViewName() != null && modelAndView.getViewName()
            .endsWith("login"))
        {
            final TbUser tbUser = (TbUser) httpServletRequest.getSession()
                .getAttribute(ConstantUtils.SESSION_USER);

            if (tbUser != null)
            {
                httpServletResponse.sendRedirect("/main");
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception
    {

    }
}
