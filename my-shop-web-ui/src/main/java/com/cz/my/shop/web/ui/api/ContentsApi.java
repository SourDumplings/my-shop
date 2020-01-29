package com.cz.my.shop.web.ui.api;

import com.cz.my.shop.commons.utils.HttpClientUtils;
import com.cz.my.shop.commons.utils.MapperUtils;
import com.cz.my.shop.web.ui.dto.TbContent;
import java.util.List;

/**
 * 内容管理接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/1/29 18:54
 */
public class ContentsApi
{

    /**
     * 请求幻灯片
     *
     * @return
     */
    public static List<TbContent> ppt()
    {
        List<TbContent> tbContents = null;
        // String result = HttpClientUtils.doGet(API.API_CONTENTS_PPT);
        String result = HttpClientUtils.doGet(API.HOST + "/contents/134");
        try
        {
            tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tbContents;
    }
}
