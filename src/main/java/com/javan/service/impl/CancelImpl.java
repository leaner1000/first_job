package com.javan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javan.dao.CancelMapper;
import com.javan.entity.EUDataGridResult;
import com.javan.entity.Cancel;
import com.javan.entity.CancelExample;
import com.javan.service.CancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CancelImpl implements CancelService {
    @Autowired
    CancelMapper fm;

    @Override
    public EUDataGridResult getPage(int pageNum, int pageSize){
        //查询客户列表
        CancelExample example = new CancelExample();
        //分页处理
        PageHelper.startPage(pageNum,pageSize);
        List<Cancel> list = fm.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Cancel> pageInfo = new PageInfo<Cancel>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Cancel getByid(int id){
        return fm.selectByPrimaryKey(id);
    }
    @Override
    public String insert(Cancel f){
        fm.insertSelective(f);
        return "";
    }
    @Override
    public String updata(Cancel f){
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
