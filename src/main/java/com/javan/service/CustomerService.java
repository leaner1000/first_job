package com.javan.service;

import com.javan.entity.EUDataGridResult;
import com.javan.entity.Customer;

public interface CustomerService {
    public EUDataGridResult getPage(int page_num, int pagesize);

    public Customer getByid(int id);

    public String insert(Customer ob);

    public String updata(Customer ob);

    public String delete_batch(Integer[] ids);
}
