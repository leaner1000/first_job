package com.javan.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javan.dao.OrderItemMapper;
import com.javan.dao.OrderMapper;
import com.javan.entity.*;
import com.javan.service.OrderItemService;
import com.javan.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class OrderController {
    @Autowired
    private OrderService fs;
    @Autowired
    private OrderItemService ois;
    @Autowired
    private OrderItemMapper oim;
    @Autowired
    private OrderMapper om;

    @RequestMapping(value = "/order/page",method = RequestMethod.POST)
    @ResponseBody
    public EUDataGridResult OrderList(Integer page,Integer rows){
        return fs.getPage(page,rows);
    }

    @RequestMapping(value = "/order/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@PathVariable Integer id){
        return fs.getByid(id);
    }

    @RequestMapping(value="/order/insert",method = RequestMethod.POST)
    @ResponseBody
    public Status insertOrder(Order f)  {
        Status s=new Status();
        om.insertSelective(f);
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/orderitem/insert",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"order:add"})
    public Status insertItem(@RequestBody OrderItem[] f)  {
        Status s=new Status();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<f.length;i++){
            oim.insertSelective(f[i]);
            if (i == 0) {
                sb.append(f[i].getOrder_id());
            }else {
                sb.append(","+f[i].getOrder_id());
            }
        }
        s.setstatus(200);
        s.setMsg(sb.toString());
        return s;
    }

    @RequestMapping(value="/order/delete_batch",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"order:cancel"})
    public Status delete(Integer[] ids){
        Order o=new Order();
        Order o1;
        OrderItem oi=new OrderItem();
        for(Integer i:ids){
            o1=om.selectByPrimaryKey(i);
            String tmp=o1.getItem_id();
            String[] s=tmp.split(",");
            if(s.length>0){
                for(String j:s){
                    oi.setOrder_id(Integer.parseInt(j));
                    oi.setStatus("cancel");
                    oim.updateByPrimaryKeySelective(oi);
                }
            }
            o.setOrder_id(i);
            o.setStatus("cancel");
            om.updateByPrimaryKeySelective(o);
        }
        Status s=new Status();
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/orderitem/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<OrderItem> orederitem(@PathVariable Integer id){
        ArrayList<OrderItem> orderItems=new ArrayList<OrderItem>();
        Order o=fs.getByid(id);
        String tmp=o.getItem_id();
        String[] s=tmp.split(",");
        for(String i:s){
            orderItems.add(ois.getByid(Integer.parseInt(i)));
        }
        Status status=new Status();
        status.setstatus(200);
        return orderItems;
    }

    @RequestMapping(value="/order/add",method = RequestMethod.GET)
    public String add(){
        return "order_add";
    }

    @RequestMapping(value="/order/edit",method = RequestMethod.GET)
    public String edit(){
        return "order_edit";
    }

    @RequestMapping(value="/order",method = RequestMethod.GET)
    public String order(){
        return "order";
    }

}
