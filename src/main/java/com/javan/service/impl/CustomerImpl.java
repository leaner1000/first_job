package com.javan.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javan.dao.CustomerMapper;
import com.javan.entity.EUDataGridResult;
import com.javan.entity.CustomerExample;
import com.javan.entity.Customer;
import com.javan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    CustomerMapper ob;
    @Override
    public EUDataGridResult getPage(int pageNum, int pageSize){
        //查询客户列表
        CustomerExample example = new CustomerExample();
        //分页处理
        PageHelper.startPage(pageNum,pageSize);
        List<Customer> list = ob.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Customer getByid(int id){
        return ob.selectByPrimaryKey(id);
    }


    @Override
    public String insert(Customer o){
        ob.insertSelective(o);
        return "";
    }
    @Override
    public String updata(Customer o){
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