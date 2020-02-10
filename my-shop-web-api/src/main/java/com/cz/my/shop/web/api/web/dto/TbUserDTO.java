package com.cz.my.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import lombok.Data;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/2/10 9:39
 */
@Data
public class TbUserDTO implements Serializable
{
    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}
