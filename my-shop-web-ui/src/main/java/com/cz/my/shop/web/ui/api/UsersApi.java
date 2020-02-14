package com.cz.my.shop.web.ui.api;

import com.cz.my.shop.commons.utils.HttpClientUtils;
import com.cz.my.shop.commons.utils.MapperUtils;
import com.cz.my.shop.web.ui.dto.TbUser;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

/**
 * 会员管理接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/2/14 18:07
 */
public class UsersApi
{

    /**
     * 登录
     *
     * @param tbUser
     * @return
     */
    public static TbUser login(TbUser tbUser) throws Exception
    {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", tbUser.getUsername()));
        params.add(new BasicNameValuePair("password", tbUser.getPassword()));

        String json = HttpClientUtils
            .doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        return user;
    }
}
