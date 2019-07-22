package com.javan.service.impl;

import com.javan.dao.SystemPermissionMapper;
import com.javan.entity.SystemPermission;
import com.javan.service.SystemPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemPermissionImpl implements SystemPermissionService {
    @Autowired
    SystemPermissionMapper spm;

    @Override
    public String getPermissionById(int id){
        SystemPermission sp=spm.selectByPrimaryKey(id);
        return sp.getPermission_name();
    }
}
