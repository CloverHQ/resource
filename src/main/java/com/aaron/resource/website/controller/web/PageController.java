package com.aaron.resource.website.controller.web;

import com.aaron.resource.website.pojo.TbAccount;
import com.aaron.resource.website.pojo.TbArticle;
import com.aaron.resource.website.pojo.TbType;
import com.aaron.resource.website.service.ArticleService;
import com.aaron.resource.website.service.TypeService;
import com.aaron.resource.website.utils.ConstantPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by huangtao on 16/12/13.
 */
@Controller
public class PageController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/")
    public String showIndex(Model model) {
        return "login";
    }

    @RequestMapping("/admin")
    public String showAdmin(Model model, HttpServletRequest request) {
        TbAccount account = (TbAccount) request.getSession().getAttribute("global.account");
//        if (account == null){
            return "login";
//        } else {
//            return "redirect:/home";
//        }
    }


    @RequestMapping("/home")
    public String home(
        Model model,
        @RequestParam(defaultValue = "0") int id
    ) {

        List<TbType> titleList = typeService.list();

        if (titleList.size() == 0){
            return "redirect:/type/home";
        }

        model.addAttribute("titleList", titleList);

        TbType defaultTitle;
        if (id == 0){
            defaultTitle = titleList.get(0);
            model.addAttribute("topTitle", defaultTitle);
        } else {
            defaultTitle = typeService.findById(id);
            model.addAttribute("topTitle", defaultTitle);
        }

        List<TbArticle> countList = articleService.listById(defaultTitle.getId());
        int pageSize = countList.size()/ConstantPool.PAGESIZE;
        model.addAttribute("totalCount", countList.size() % ConstantPool.PAGESIZE==0?pageSize:pageSize+1);

        List<TbArticle> itemList = articleService.listById(defaultTitle.getId(), 1);

        for (TbArticle tbArticle : itemList) {
            if (tbArticle.getContent() != null && tbArticle.getContent().length() > 50) {
                tbArticle.setContent(tbArticle.getContent().substring(0, 50));
            }
        }

        model.addAttribute("itemList", itemList);
        return "home";
    }

}
