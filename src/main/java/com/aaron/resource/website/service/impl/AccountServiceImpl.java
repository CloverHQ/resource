package com.aaron.resource.website.service.impl;

import com.aaron.resource.website.mapper.TbAccountMapper;
import com.aaron.resource.website.pojo.TbAccount;
import com.aaron.resource.website.pojo.TbAccountExample;
import com.aaron.resource.website.service.AccountService;
import com.aaron.resource.website.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
/**
 * Created by huangtao on 16/12/13.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private TbAccountMapper tbAccountMapper;

    @Override
    public TbAccount login(String username, String password) {
        TbAccountExample example = new TbAccountExample();
        TbAccountExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username).andPasswordEqualTo(Md5Utils.encode(password));
        List<TbAccount> list = tbAccountMapper.selectByExample(example);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void save(String username, String password) {
        TbAccount tbAccount = new TbAccount();
        tbAccount.setUsername(username);
        tbAccount.setPassword(Md5Utils.encode(password));

        Date currentDate = new Date();
        tbAccount.setCreattime(currentDate);
        tbAccount.setUpdattime(currentDate);

        tbAccountMapper.insert(tbAccount);
    }
}
