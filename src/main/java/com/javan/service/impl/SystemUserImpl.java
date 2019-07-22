package com.javan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javan.dao.SystemUserMapper;
import com.javan.entity.EUDataGridResult;
import com.javan.entity.SystemUser;
import com.javan.entity.SystemUserExample;
import com.javan.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserImpl implements SystemUserService {
    @Autowired
    SystemUserMapper ob;

    @Override
    public SystemUser getByName(String name){
        SystemUserExample example=new SystemUserExample();
        example.createCriteria().andUsernameEqualTo(name);
        Object[] su=ob.selectByExample(example).toArray();
        return (SystemUser)su[0];
    }

    @Override
    public EUDataGridResult getPage(int pageNum, int pageSize){
        //查询客户列表
        SystemUserExample example = new SystemUserExample();
        //分页处理
        PageHelper.startPage(pageNum,pageSize);
        List<SystemUser> list = ob.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<SystemUser> pageInfo = new PageInfo<SystemUser>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public SystemUser getByid(int id){
        return ob.selectByPrimaryKey(id);
    }


    @Override
    public String insert(SystemUser o){
        ob.insertSelective(o);
        return "";
    }
    @Override
    public String updata(SystemUser o){
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
