package com.javan.service.impl;

import com.javan.dao.SystemRoleMapper;
import com.javan.entity.SystemRole;
import com.javan.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SystemRoleImpl implements SystemRoleService {
    @Autowired
    SystemRoleMapper srm;
    String permission_spilit=",";

    @Override
    public ArrayList<Integer> getPermssion(int roleid){
        SystemRole sr= srm.selectByPrimaryKey(roleid);
        String[] permission_list=sr.getPermissions().split(",");
        ArrayList<Integer> per_list=new ArrayList<Integer>();
        for(String permisson:permission_list){
            per_list.add(Integer.parseInt(permisson));
        }
        return per_list;
    }

}
