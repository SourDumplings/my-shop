package com.cz.my.shop.web.api.web.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/1/26 22:04
 */
@Data
public class TbContentDTO implements Serializable
{
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}