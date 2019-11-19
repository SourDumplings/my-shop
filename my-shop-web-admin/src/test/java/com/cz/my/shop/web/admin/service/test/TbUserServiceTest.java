package com.cz.my.shop.web.admin.service.test;

import com.cz.my.shop.domain.TbUser;
import com.cz.my.shop.web.admin.service.TbUserService;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/9 8:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml",
    "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest
{
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll()
    {
        final List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers)
        {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert()
    {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("czTest");
        tbUser.setPhone("123456778");
        tbUser.setEmail("czTest@cz.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.insert(tbUser);
    }

    @Test
    public void testDelete()
    {
        tbUserService.delete(38L);
    }

    @Test
    public void testGetById()
    {
        final TbUser tbUser = tbUserService.getById(32L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void update()
    {
        final TbUser tbUser = tbUserService.getById(34L);
        tbUser.setUsername("czTest11");
        tbUserService.update(tbUser);
    }

    @Test
    public void testLogin()
    {
        final TbUser czTest = tbUserService.login("czTest@cz.com", "123456");
        System.out.println(czTest == null ? "登录失败" : "登录成功");
    }
}
