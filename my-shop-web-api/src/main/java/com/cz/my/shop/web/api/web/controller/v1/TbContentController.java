package com.cz.my.shop.web.api.web.controller.v1;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.domain.TbContent;
import com.cz.my.shop.web.api.service.TbContentService;
import com.cz.my.shop.web.api.web.dto.TbContentDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "${api.path.v1}/contents")
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

    @RequestMapping(value = "{category_id}", method = RequestMethod.GET)
    public BaseResult findContentByCategoryId(
        @PathVariable(value = "category_id") Long categoryId)
    {
        BaseResult res = null;
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

            res = BaseResult.success("成功", tbContentDTOS);

        }
        return res;

    }
}
