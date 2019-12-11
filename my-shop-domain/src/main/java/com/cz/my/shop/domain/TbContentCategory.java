package com.cz.my.shop.domain;

import com.cz.my.shop.commons.persistence.BaseEntity;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

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

    @Length(min = 1, max = 20, message = "分类名称必须介于 1 - 20 位之间")
    private String name;

    private Integer status;

    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

    private Boolean isParent;
    private TbContentCategory parent;
}
