package com.aaron.resource.website.service;


import com.aaron.resource.website.pojo.TbAccount;

/**
 * Created by huangtao on 16/12/13.
 */
public interface AccountService {
    TbAccount login(String username, String password);

    void save(String username, String password) throws Exception;
}
