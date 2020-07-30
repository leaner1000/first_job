package com.javan.service;

import com.javan.entity.EUDataGridResult;
import com.javan.entity.Outstockinfo;

public interface OutstockinfoService {
    public EUDataGridResult getPage(int page_num, int pagesize);

    public Outstockinfo getByid(int id);

    public String insert(Outstockinfo ob);

    public String updata(Outstockinfo ob);

    public String delete_batch(Integer[] ids);
}
