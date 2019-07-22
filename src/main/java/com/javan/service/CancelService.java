package com.javan.service;

import com.javan.entity.Cancel;
import com.javan.entity.EUDataGridResult;

public interface CancelService {
    public EUDataGridResult getPage(int page_num, int pagesize);

    public Cancel getByid(int id);

    public String insert(Cancel f);

    public String updata(Cancel f);

    public String delete_batch(Integer[] ids);
}
