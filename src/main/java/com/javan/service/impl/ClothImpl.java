package com.javan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javan.dao.ClothMapper;
import com.javan.entity.Cloth;
import com.javan.entity.ClothExample;
import com.javan.entity.EUDataGridResult;
import com.javan.service.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClothImpl implements ClothService {
    @Autowired
    ClothMapper fm;

    @Override
    public EUDataGridResult getPage(int pageNum,int pageSize){
        //查询客户列表
        ClothExample example = new ClothExample();
        //分页处理
        PageHelper.startPage(pageNum,pageSize);
        List<Cloth> list = fm.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Cloth> pageInfo = new PageInfo<Cloth>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Cloth getByid(int id){
        return fm.selectByPrimaryKey(id);
    }
    @Override
    public String insert(Cloth f){
        fm.insertSelective(f);
        return "";
    }
    @Override
    public String updata(Cloth f){
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
