package com.javan.service.impl;

        import com.github.pagehelper.PageHelper;
        import com.github.pagehelper.PageInfo;
        import com.javan.dao.OrderMapper;
        import com.javan.entity.EUDataGridResult;
        import com.javan.entity.Order;
        import com.javan.entity.OrderExample;
        import com.javan.service.OrderService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;


        import java.util.List;

@Service
public class OrderImpl implements OrderService {
    @Autowired
    OrderMapper fm;

    @Override
    public EUDataGridResult getPage(int pageNum, int pageSize){
        //查询客户列表
        OrderExample example = new OrderExample();
        example.setOrderByClause("order_id desc");
        //分页处理
        PageHelper.startPage(pageNum,pageSize);
        List<Order> list = fm.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Order> pageInfo = new PageInfo<Order>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Order getByid(int id){
        return fm.selectByPrimaryKey(id);
    }
    @Override
    public String insert(Order f){
        fm.insertSelective(f);
        return "";
    }
    @Override
    public String updata(Order f){
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
