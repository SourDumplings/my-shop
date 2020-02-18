package com.cz.my.shop.commons.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 邮件发送工具类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/2/18 8:41
 */
public class EmailSendUtils
{

    @Autowired
    private Email email;

    public void send(String subject, String msg, String... to) throws EmailException
    {
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }
}
