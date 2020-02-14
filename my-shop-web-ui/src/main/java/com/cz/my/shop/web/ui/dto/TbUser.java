package com.cz.my.shop.web.ui.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/2/14 18:31
 */
@Data
public class TbUser implements Serializable
{
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String verification;
}
