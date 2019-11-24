package com.cz.my.shop.domain;

import com.cz.my.shop.commons.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/9 8:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TbUser extends BaseEntity implements Serializable
{
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    private String content;
}
