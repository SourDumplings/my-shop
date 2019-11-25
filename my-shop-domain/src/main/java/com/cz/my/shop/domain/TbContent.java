package com.cz.my.shop.domain;

import com.cz.my.shop.commons.persistence.BaseEntity;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

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
    @NotNull(message = "父级类目不能为空")
    private String categoryId;

    @Length(min = 1, max = 20, message = "标题长度介于 1 - 20 个字符之间")
    private String title;

    @Length(min = 1, max = 20, message = "子标题长度介于 1 - 20 个字符之间")
    private String subTitle;

    @Length(min = 1, max = 50, message = "标题描述长度介于 1 - 50 个字符之间")
    private String titleDesc;

    private String url;
    private String pic;
    private String pic2;

    // @Length(min = 1, message = "内容不能为空")
    private String content;

/*    @NotNull(message = "父级类目不能为空")
    private TbContentCategory tbContentCategory;*/

}
