package com.cz.my.shop.web.ui.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/1/29 17:31
 */
@Data
public class TbContent implements Serializable
{
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
