package com.javan.controller;

import com.javan.dao.CustomerMapper;
import com.javan.dao.PaymentMapper;
import com.javan.entity.*;
import com.javan.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService ob;
    @Autowired
    private CustomerMapper om;
    @Autowired
    private PaymentMapper pm;

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

    @RequestMapping(value = "/customerpayment/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Payment> getpayment(@PathVariable Integer id){
        ArrayList<Payment> pl=new ArrayList<Payment>();
        Customer c=om.selectByPrimaryKey(id);
        String tmp=c.getUnused2();
        String[] l=tmp.split(",");
        for(String i:l){
            if(i.length()==0){
                continue;
            }
            Payment p=pm.selectByPrimaryKey(Integer.parseInt(i));
            pl.add(p);

        }
        return pl;
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
        o.setUnused2("");
        o.setUnused1(0);
        Status s=new Status();
        om.insertSelective(o);
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/customer/update",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"customer:edit"})
    public Status update(@RequestBody Customer o){
        Customer c=om.selectByPrimaryKey(o.getCustomer_id());

        if(c.getUnused1().compareTo(o.getUnused1())!=0){
            Payment p=new Payment();
            p.setAmount(o.getUnused1()-c.getUnused1());
            p.setDate(new Date());




            pm.insertSelective(p);
            o.setUnused2(o.getUnused2()==null?""+p.getPayment_id():p.getPayment_id()+","+o.getUnused2());
            ob.updata(o);
        }else{
            ob.updata(o);
        }
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
