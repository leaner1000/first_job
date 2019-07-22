package com.javan.service;

import com.javan.entity.CancelItem;

public interface CancelItemService {
    public CancelItem getByid(int id);

    public String insert(CancelItem f);

    public String updata(CancelItem f);

    public String delete_batch(Integer[] ids);
}
