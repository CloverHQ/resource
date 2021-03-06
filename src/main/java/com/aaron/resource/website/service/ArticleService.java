package com.aaron.resource.website.service;

import com.aaron.resource.website.pojo.TbArticle;

import java.util.List;

/**
 * Created by huangtao on 17/1/7.
 */
public interface ArticleService {

    List<TbArticle> listById(int id);

    List<TbArticle> listById(int id, int now);

    void save(TbArticle tbArticle) throws Exception;

    void deleteById(Integer id) throws Exception;

    TbArticle findById(int id);

    void update(TbArticle tbArticle);

    List<TbArticle> listBanner();

    List<TbArticle> listByIdApi(int id, int now);
}
