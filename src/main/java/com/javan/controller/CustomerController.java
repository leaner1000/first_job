package com.javan.controller;

import com.javan.dao.CustomerMapper;
import com.javan.entity.CustomerExample;
import com.javan.entity.EUDataGridResult;
import com.javan.entity.Customer;
import com.javan.entity.Status;
import com.javan.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService ob;
    @Autowired
    private CustomerMapper om;

    @RequestMapping(value = "/customer/page",method = RequestMethod.POST)
    @ResponseBody
    public EUDataGridResult OutboundList(Integer page,Integer rows){
        return ob.getPage(page,rows);
    }

    @RequestMapping(value = "/customer/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Customer getOB(@PathVariable Integer id){
        return ob.getByid(id);
    }

    @RequestMapping(value = "/customer/autocomplete",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<?> autoComplete(@Param("q") String q){
        if(q==null||q.length()==0){
            return ob.getPage(1,100).getRows();
        }
        return om.autoComplete(q);
    }

    @RequestMapping(value="/customer/insert",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"customer:add"})
    public Status insert(Customer o){
        Status s=new Status();
        om.insertSelective(o);
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/customer/update",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"customer:edit"})
    public Status update(Customer o){
        ob.updata(o);
        Status s=new Status();
        s.setstatus(200);
        return s;
    }
    @RequestMapping(value="/customer/delete_batch",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"customer:delete"})
    public Status delete(Integer[] ids){
        ob.delete_batch(ids);
        Status s=new Status();
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/customer/add",method = RequestMethod.GET)
    public String add(){
        return "customer_add";
    }

    @RequestMapping(value="/customer",method = RequestMethod.GET)
    public String furniture(){
        return "customer";
    }

    @RequestMapping(value="/customer/edit",method = RequestMethod.GET)
    public String edit(){
        return "customer_edit";
    }
}
