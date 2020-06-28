package com.aaron.resource.website.controller.web;

import com.aaron.resource.website.pojo.ListByIdBean;
import com.aaron.resource.website.pojo.TbArticle;
import com.aaron.resource.website.pojo.TbType;
import com.aaron.resource.website.properties.MiniProgramProperties;
import com.aaron.resource.website.service.ArticleService;
import com.aaron.resource.website.service.TypeService;
import com.aaron.resource.website.utils.ConstantPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private MiniProgramProperties miniProgramProperties;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/wechat/user/login/{code}")
    public Object wechatLogin(@PathVariable String code) {
        final String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        return restTemplate.getForEntity(url, String.class, miniProgramProperties.getAppId(), miniProgramProperties.getAppSecret(), code).getBody();
    }

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

        return tbArticle;
    }


    @RequestMapping("list")
    public List<TbArticle> showIndex(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        return articleService.listBanner();
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

        for (TbArticle tbArticle : tbArticleList) {
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

        for (TbArticle tbArticle : tbArticleList) {
            if (tbArticle.getContent() != null && tbArticle.getContent().length() > 50) {
                tbArticle.setContent(tbArticle.getContent().substring(0, 50));
            }

        }
        return tbArticleList;

    }
}
