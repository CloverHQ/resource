package com.aaron.resource.website.controller.web;

import com.aaron.resource.website.pojo.TbArticle;
import com.aaron.resource.website.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by huangtao on 17/1/8.
 */
@Controller
@RequestMapping("/banner/")
public class BannerController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping("list")
    @ResponseBody
    public List<TbArticle> showIndex() {
        return articleService.listBanner();
    }


    @RequestMapping("home")
    public String bannerHome(Model model) {
        List<TbArticle> tbArticles = articleService.listBanner();
        model.addAttribute("bannerList", tbArticles);
        return "bannerHome";
    }
}
