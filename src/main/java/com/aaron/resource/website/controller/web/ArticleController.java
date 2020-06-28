package com.aaron.resource.website.controller.web;

import com.aaron.resource.website.properties.AliOssProperties;
import com.aaron.resource.website.pojo.TbArticle;
import com.aaron.resource.website.pojo.TbType;
import com.aaron.resource.website.service.ArticleService;
import com.aaron.resource.website.service.TypeService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by huangtao on 17/1/7.
 */
@Controller
@RequestMapping("/item/")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private AliOssProperties aliOssProperties;


    @RequestMapping("showArticleSave")
    public String show(
            Model model,
            @RequestParam(defaultValue = "0") int id,
            @RequestParam(defaultValue = "0") int isAddBanner) {
        List<TbType> titleList = typeService.list();

        if (titleList.size() == 0) {
            return "redirect:/type/showSavaPage";
        }

        model.addAttribute("titleList", titleList);


        if (id != 0) {
            TbArticle tbArticle = articleService.findById(id);
            model.addAttribute("article", tbArticle);
        }

        model.addAttribute("isAddBanner", isAddBanner);

        return "articleEdit";
    }

    @RequestMapping("save")
    public String save(
            @ModelAttribute TbArticle tbArticle,
            Model model,
            @RequestParam(value = "logo", required = false) MultipartFile logo,
            HttpServletRequest request) {
        try {
            if (logo != null && !logo.isEmpty()) {
                // 获取文件的后缀名
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String filename = logo.getOriginalFilename();
                String suffixName = filename.substring(filename.lastIndexOf("."));
                // 生成上传文件名
                String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
                String objectName = sdf.format(new Date()) + "/" + finalFileName;
                OSS ossClient = new OSSClientBuilder().build(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(), aliOssProperties.getAccessKeySecret());
                final String bucketName = aliOssProperties.getBucketName();
                ossClient.putObject(bucketName, objectName, logo.getInputStream());
                // 设置URL过期时间。
                Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24 * 365 * 10);
                // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
                URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
                ossClient.shutdown();
                tbArticle.setImgurl(url.toString());
            }

            if (tbArticle.getId() != null && tbArticle.getId() != 0) {
                articleService.update(tbArticle);
            } else {
                articleService.save(tbArticle);
            }

            if (tbArticle.getPid() == null) {
                return "redirect:/banner/home";
            }

            return "redirect:/home?id=" + tbArticle.getPid();
        } catch (Exception e) {
            log.error("", e);
            model.addAttribute("article", tbArticle);
            model.addAttribute("error", "插入或更新失败 " + e.getMessage() + "");
            return "articleEdit";
        }
    }

    @RequestMapping("deleteById")
    public String deleteById(Integer id, Integer pid) {
        try {
            articleService.deleteById(id);
        } catch (Exception e) {
            log.error("", e);
        }

        if (pid == null) {
            pid = 0;
        }

        return "redirect:/home?id=" + pid;
    }

}
