package com.javan.service;

import com.javan.entity.Cloth;
import com.javan.entity.EUDataGridResult;

public interface ClothService {
    public EUDataGridResult getPage(int page_num,int pagesize);

    public Cloth getByid(int id);

    public String insert(Cloth f);

    public String updata(Cloth f);

    public String delete_batch(Integer[] ids);
}
