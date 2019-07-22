package com.javan.service.impl;

import com.javan.dao.CancelItemMapper;
import com.javan.entity.CancelItem;
import com.javan.service.CancelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CancelItemImpl implements CancelItemService {
    @Autowired
    CancelItemMapper fm;

    @Override
    public CancelItem getByid(int id){
        return fm.selectByPrimaryKey(id);
    }
    @Override
    public String insert(CancelItem f){
        fm.insertSelective(f);
        return "";
    }
    @Override
    public String updata(CancelItem f){
        fm.updateByPrimaryKeySelective(f);
        return "";
    }
    @Override
    public String delete_batch(Integer[] ids){
        for(int i:ids){
            fm.deleteByPrimaryKey(i);
        }
        return "";
    }
}
