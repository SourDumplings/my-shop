package com.cz.my.shop.web.admin.web.interceptor;

import com.cz.my.shop.commons.constant.ConstantUtils;
import com.cz.my.shop.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/6 10:04
 */
public class LoginInterceptor implements HandlerInterceptor
{

    public boolean preHandle(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Object o) throws Exception
    {
        final User user = (User) httpServletRequest.getSession()
            .getAttribute(ConstantUtils.SESSION_USER);

        // 未登录
        if (user == null)
        {
            // 去登录页
            httpServletResponse.sendRedirect("/login");
        }

        // 放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
        throws Exception
    {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception
    {

    }
}
