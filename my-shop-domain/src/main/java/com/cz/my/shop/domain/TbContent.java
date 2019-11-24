package com.cz.my.shop.domain;

import com.cz.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 内容实体类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/22 14:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TbContent extends BaseEntity
{
    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
