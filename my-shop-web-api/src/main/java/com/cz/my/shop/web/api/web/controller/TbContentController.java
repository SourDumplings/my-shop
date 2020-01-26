package com.cz.my.shop.web.api.web.controller;

import com.cz.my.shop.domain.TbContent;
import com.cz.my.shop.web.api.service.TbContentService;
import com.cz.my.shop.web.api.web.dto.TbContentDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/1/19 10:54
 */
@RestController
@RequestMapping(value = "content")
public class TbContentController
{
    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id)
    {
        TbContent tbContent = null;

        if (id == null)
        {
            tbContent = new TbContent();
        }

        return tbContent;
    }

    @RequestMapping(value = "findContentByCategoryId", method = RequestMethod.GET)
    public List<TbContentDTO> findContentByCategoryId(Long categoryId)
    {
        List<TbContentDTO> tbContentDTOS = null;
        final List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);

        if (tbContents != null && !tbContents.isEmpty())
        {
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents)
            {
                TbContentDTO tbContentDTO = new TbContentDTO();
                BeanUtils.copyProperties(tbContent, tbContentDTO);
                tbContentDTOS.add(tbContentDTO);
            }

        }
        return tbContentDTOS;

    }
}
