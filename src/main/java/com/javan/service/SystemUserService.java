package com.javan.service;

import com.javan.entity.EUDataGridResult;
import com.javan.entity.SystemUser;

public interface SystemUserService {
    public SystemUser getByName(String name);

    public SystemUser getByid(int id);

    public EUDataGridResult getPage(int page_num, int pagesize);

    public String insert(SystemUser user);

    public String updata(SystemUser user);

    public String delete_batch(Integer[] ids);
}
