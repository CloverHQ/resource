package com.aaron.resource.website.service.impl;

import com.aaron.resource.website.mapper.TbTypeMapper;
import com.aaron.resource.website.pojo.TbType;
import com.aaron.resource.website.pojo.TbTypeExample;
import com.aaron.resource.website.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by huangtao on 17/1/7.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TbTypeMapper tbTypeMapper;

    @Override
    public List<TbType> list() {
        TbTypeExample example = new TbTypeExample();
        example.setOrderByClause("weight");
        return tbTypeMapper.selectByExample(example);
    }

    @Override
    public void saveTitle(TbType tbListTitle) throws Exception {
        Date currentDate = new Date();
        tbListTitle.setCreattime(currentDate);
        tbListTitle.setUpdattime(currentDate);
        tbTypeMapper.insert(tbListTitle);
    }

    @Override
    public TbType findById(int id) {
        return tbTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateTitle(TbType tbListTitle) {
        tbTypeMapper.updateByPrimaryKey(tbListTitle);
    }

    @Override
    public void deleteById(Integer id) throws Exception{
        tbTypeMapper.deleteByPrimaryKey(id);
    }


}
