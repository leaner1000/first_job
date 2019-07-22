package com.javan.controller;

import com.javan.dao.SystemUserMapper;
import com.javan.entity.EUDataGridResult;
import com.javan.entity.SystemUser;
import com.javan.entity.Status;
import com.javan.service.SystemUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class UserController {
    @Autowired
    private SystemUserService ob;
    @Autowired
    private SystemUserMapper sum;

    @RequestMapping(value = "/user/page",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:scan")
    public EUDataGridResult OutboundList(Integer page,Integer rows){
        return ob.getPage(page,rows);
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("user:scan")
    public SystemUser getOB(@PathVariable Integer id){
        return ob.getByid(id);
    }

    @RequestMapping(value="/user/insert",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"user:add"})
    public Status insert(SystemUser o){
        Status s=new Status();
        sum.insertSelective(o);
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/user/update",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"user:edit"})
    public Status update(SystemUser o){
        sum.updateByPrimaryKey(o);
        Status s=new Status();
        s.setstatus(200);
        return s;
    }
    @RequestMapping(value="/user/delete_batch",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"user:delete"})
    public Status delete(Integer[] ids){
        ob.delete_batch(ids);
        Status s=new Status();
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/user/add",method = RequestMethod.GET)
    public String add(){
        return "user_add";
    }


    @RequestMapping(value="/user",method = RequestMethod.GET)
    public String furniture(){
        return "user";
    }

    @RequestMapping(value="/user/edit",method = RequestMethod.GET)
    public String edit(){
        return "user_edit";
    }


}
