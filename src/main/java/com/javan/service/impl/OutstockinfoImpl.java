package com.javan.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javan.dao.OutstockinfoMapper;
import com.javan.entity.EUDataGridResult;
import com.javan.entity.OutstockinfoExample;
import com.javan.entity.Outstockinfo;
import com.javan.service.OutstockinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OutstockinfoImpl implements OutstockinfoService {
    @Autowired
    OutstockinfoMapper ob;
    @Override
    public EUDataGridResult getPage(int pageNum, int pageSize){
        //查询客户列表
        OutstockinfoExample example = new OutstockinfoExample();
        //分页处理
        PageHelper.startPage(pageNum,pageSize);
        List<Outstockinfo> list = ob.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Outstockinfo> pageInfo = new PageInfo<Outstockinfo>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Outstockinfo getByid(int id){
        return ob.selectByPrimaryKey(id);
    }


    @Override
    public String insert(Outstockinfo o){
        ob.insertSelective(o);
        return "";
    }
    @Override
    public String updata(Outstockinfo o){
        ob.updateByPrimaryKeySelective(o);
        return "";
    }

    @Override
    public String delete_batch(Integer [] ids){
        for(int i:ids){
            ob.deleteByPrimaryKey(i);
        }
        return "";
    }
}