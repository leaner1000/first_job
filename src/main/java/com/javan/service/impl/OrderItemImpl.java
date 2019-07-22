package com.javan.service.impl;

import com.javan.dao.OrderItemMapper;
import com.javan.entity.OrderItem;
import com.javan.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderItemImpl implements OrderItemService {
    @Autowired
    OrderItemMapper fm;

    @Override
    public OrderItem getByid(int id){
        return fm.selectByPrimaryKey(id);
    }
    @Override
    public String insert(OrderItem f){
        fm.insertSelective(f);
        return "";
    }
    @Override
    public String updata(OrderItem f){
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
