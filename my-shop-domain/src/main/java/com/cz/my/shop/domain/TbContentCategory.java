package com.cz.my.shop.domain;

import com.cz.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/20 8:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TbContentCategory extends BaseEntity
{
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;
    private TbContentCategory parent;
}
