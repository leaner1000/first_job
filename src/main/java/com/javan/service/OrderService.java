package com.javan.service;

import com.javan.entity.Order;
import com.javan.entity.EUDataGridResult;

public interface OrderService {
    public EUDataGridResult getPage(int page_num, int pagesize);

    public Order getByid(int id);

    public String insert(Order f);

    public String updata(Order f);

    public String delete_batch(Integer[] ids);
}
