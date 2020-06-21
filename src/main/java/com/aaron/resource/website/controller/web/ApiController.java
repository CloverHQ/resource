package com.aaron.resource.website.controller.web;

import com.aaron.resource.website.pojo.ListByIdBean;
import com.aaron.resource.website.pojo.TbArticle;
import com.aaron.resource.website.pojo.TbType;
import com.aaron.resource.website.service.ArticleService;
import com.aaron.resource.website.service.TypeService;
import com.aaron.resource.website.utils.ConstantPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by huangtao on 17/1/9.
 */
@RestController
@RequestMapping("/api/")
public class ApiController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("findByIdApi")
    public TbArticle findByIdApi(
            HttpServletResponse response,
            int id
    ) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        TbArticle tbArticle = articleService.findById(id);


        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
        tbArticle.setDate(simpleFormatter.format(tbArticle.getUpdattime()));

        tbArticle.setImgurl(ConstantPool.IP + tbArticle.getImgurl());
        return tbArticle;
    }


    @RequestMapping("list")
    public List<TbArticle> showIndex(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        List<TbArticle> tbArticles = articleService.listBanner();

        for (TbArticle tbArticle : tbArticles) {
            tbArticle.setImgurl(ConstantPool.IP + tbArticle.getImgurl());
        }

        return tbArticles;
    }

    @Autowired
    private TypeService tbListTitleService;

    @RequestMapping("listApiType")
    public List<TbType> listApi(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        return tbListTitleService.list();
    }

    @RequestMapping(value = "listById")
    public ListByIdBean listById(
            int id,
            HttpServletResponse response,
            @RequestParam(defaultValue = "1") int now
    ) {
        List<TbArticle> tbArticleList = articleService.listById(id, now);

        for (int i = 0; i < tbArticleList.size(); i++) {
            TbArticle tbArticle = tbArticleList.get(i);
            if (tbArticle.getContent() != null && tbArticle.getContent().length() > 50) {
                tbArticle.setContent(tbArticle.getContent().substring(0, 50));
            }
        }

        ListByIdBean listByIdBean = new ListByIdBean();
        listByIdBean.setList(tbArticleList);

        List<TbArticle> countList = articleService.listById(id);
        int pageSize = countList.size() / ConstantPool.PAGESIZE;
        listByIdBean.setPageCount(countList.size() % ConstantPool.PAGESIZE == 0 ? pageSize : pageSize + 1);


        return listByIdBean;
    }


    @RequestMapping(value = "listApiArticle")
    public List<TbArticle> listApiArticle(
            int id,
            HttpServletResponse response,
            @RequestParam(defaultValue = "0") int loadTime
    ) {
        List<TbArticle> tbArticleList = articleService.listByIdApi(id, loadTime + 1);

        for (int i = 0; i < tbArticleList.size(); i++) {
            TbArticle tbArticle = tbArticleList.get(i);
            if (tbArticle.getContent() != null && tbArticle.getContent().length() > 50) {
                tbArticle.setContent(tbArticle.getContent().substring(0, 50));
            }

            tbArticle.setImgurl(ConstantPool.IP + tbArticle.getImgurl());
        }
        return tbArticleList;

    }
}
