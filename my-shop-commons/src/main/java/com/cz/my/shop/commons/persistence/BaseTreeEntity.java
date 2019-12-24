package com.cz.my.shop.commons.persistence;

import java.io.Serializable;
import lombok.Data;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/12/24 21:58
 */
@Data
public class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable
{
    private T parent;
    private Boolean isParent;
}
