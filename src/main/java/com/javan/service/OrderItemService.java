package com.javan.service;

import com.javan.entity.OrderItem;

public interface OrderItemService {
    public OrderItem getByid(int id);

    public String insert(OrderItem f);

    public String updata(OrderItem f);

    public String delete_batch(Integer[] ids);
}
