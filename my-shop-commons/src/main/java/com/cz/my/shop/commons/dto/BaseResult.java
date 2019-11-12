package com.cz.my.shop.commons.dto;

import java.io.Serializable;

/**
 * 自定义返回结果
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/12 8:59
 */
public class BaseResult implements Serializable
{
    private int status;
    private String message;

    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    public static BaseResult success()
    {
        return createResult(STATUS_SUCCESS, "成功");
    }

    public static BaseResult success(String message)
    {
        return createResult(STATUS_SUCCESS, message);
    }

    public static BaseResult fail()
    {
        return createResult(STATUS_FAIL, "失败");
    }

    public static BaseResult fail(String message)
    {
        return createResult(STATUS_FAIL, message);
    }

    public static BaseResult fail(int status, String message)
    {
        return createResult(status, message);
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    private static BaseResult createResult(int status, String message)
    {
        BaseResult baseResult = new BaseResult();
        baseResult.setMessage(message);
        baseResult.setStatus(status);
        return baseResult;
    }
}
