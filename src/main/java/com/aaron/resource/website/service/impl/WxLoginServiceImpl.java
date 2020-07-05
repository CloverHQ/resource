package com.aaron.resource.website.service.impl;

import com.aaron.resource.website.dto.WxLoginVoucher;
import com.aaron.resource.website.mapper.TbWxAccountMapper;
import com.aaron.resource.website.pojo.TbWxAccount;
import com.aaron.resource.website.pojo.TbWxAccountExample;
import com.aaron.resource.website.service.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxLoginServiceImpl implements WxLoginService {
    @Autowired
    private TbWxAccountMapper tbWxAccountMapper;

    @Override
    public void saveLoginUser(WxLoginVoucher wxLoginVoucher) {
        TbWxAccount tbWxAccount = new TbWxAccount();
        TbWxAccountExample example = new TbWxAccountExample();
        example.createCriteria().andOpenidEqualTo(wxLoginVoucher.getOpenid());
        List<TbWxAccount> tbWxAccounts = tbWxAccountMapper.selectByExample(example);
        if (tbWxAccounts.isEmpty()) {
            tbWxAccount.setOpenid(wxLoginVoucher.getOpenid());
            tbWxAccount.setSessionKey(wxLoginVoucher.getSession_key());
            tbWxAccountMapper.insertSelective(tbWxAccount);
        } else {
            TbWxAccount account = tbWxAccounts.get(0);
            account.setSessionKey(wxLoginVoucher.getSession_key());
            tbWxAccountMapper.updateByExampleSelective(account, example);
        }
    }
}
