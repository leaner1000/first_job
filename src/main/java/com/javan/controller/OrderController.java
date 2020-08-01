package com.javan.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javan.dao.*;
import com.javan.entity.*;
import com.javan.service.OrderItemService;
import com.javan.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private CustomerMapper cm;
    @Autowired
    private SpecialPriceMapper spm;
    @Autowired
    private ClothMapper clm;

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
        Float total=0f;
        om.insertSelective(f);
        if(f.getCustomer_name().length()!=0){                         //改变用户应付款
            CustomerExample ce=new CustomerExample();
            ce.createCriteria().andCustomer_nameEqualTo(f.getCustomer_name());
            List<Customer> lc=cm.selectByExample(ce);
            String tmp=f.getItem_id();
            String[] l=tmp.split(",");
            if(l.length>0){                                         //统计应付总金额
                for(String j:l){
                    OrderItem oi=oim.selectByPrimaryKey(Integer.parseInt(j));

                    SpecialPriceKey spk=new SpecialPriceKey();    //修改默认价格
                    spk.setCloth_id(oi.getCloth_id());
                    spk.setCustom_id(f.getCustomer_name());
                    SpecialPrice sp = spm.selectByPrimaryKey(spk);
                    if(sp==null){
                        if(!clm.selectByPrimaryKey(oi.getCloth_id()).equals(oi.getSingle())){
                            sp=new SpecialPrice();
                            sp.setCloth_id(oi.getCloth_id());
                            sp.setCustom_id(spk.getCustom_id());
                            sp.setDefault_price(BigDecimal.valueOf(oi.getSingle()));
                            spm.insert(sp);
                        }
                    }else{
                        sp=new SpecialPrice();
                        sp.setCloth_id(oi.getCloth_id());
                        sp.setCustom_id(spk.getCustom_id());
                        sp.setDefault_price(BigDecimal.valueOf(oi.getSingle()));
                        spm.updateByPrimaryKeySelective(sp);
                    }
                    total+=oi.getTotal();
                }
            }
            for(Customer c:lc){                             //改变应付款，减去总计金额
                c.setUnused1(c.getUnused1()-total.intValue());
                if(f.getDes()!=null&&!f.getDes().equals("")){   //des为运费，有含义变更
                    c.setUnused1(c.getUnused1()-Integer.parseInt(f.getDes()));
                }
                cm.updateByPrimaryKeySelective(c);
            }
        }

        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/complete_order",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"order:complete"})
    public Status completeOrder(Integer[] ids){
        Order o=new Order();
        Order o1;
        Float total=0f;
        List<Customer> lc=null;
        OrderItem oi;
        for(Integer i:ids){                                  //改变用户应付款
            total=0f;
            o1=om.selectByPrimaryKey(i);
            if(!o1.getStatus().equals("complete")){   //订单有效
                continue;
            }
            if(o1.getCustomer_name().length()!=0){
                CustomerExample ce=new CustomerExample();
                ce.createCriteria().andCustomer_nameEqualTo(o1.getCustomer_name());
                lc=cm.selectByExample(ce);
            }
            String tmp=o1.getItem_id();
            if(tmp.length()>0){
                String[] s=tmp.split(",");
                if(s.length>0){
                    for(String j:s){                    //统计总金额
                        oi=oim.selectByPrimaryKey(Integer.parseInt(j));
                        if(!oi.getStatus().equals("complete")){
                            continue;
                        }
                        total+=oi.getTotal();
                        oi.setStatus("payed");
                        oim.updateByPrimaryKeySelective(oi);
                    }
                }
            }
            if(lc!=null){
                for(Customer c:lc){
                    c.setUnused1(c.getUnused1()+total.intValue());
                    if(o1.getDes()!=null&&!o1.getDes().equals("")){                    //运费
                        c.setUnused1(c.getUnused1()+Integer.parseInt(o1.getDes()));
                    }
                    cm.updateByPrimaryKeySelective(c);
                }
            }
            o.setOrder_id(i);
            o.setStatus("payed");
            om.updateByPrimaryKeySelective(o);
        }
        Status s=new Status();
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
            if(f[i].getSingle()==null){
                f[i].setSingle(0f);
            }
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
        Float total=0f;
        List<Customer> lc=null;
        OrderItem oi;
        for(Integer i:ids){                                  //改变用户应付款
            total=0f;
            o1=om.selectByPrimaryKey(i);
            if(o1.getStatus().equals("cancel")){   //订单已撤销
                continue;
            }
            if(o1.getCustomer_name().length()!=0){
                CustomerExample ce=new CustomerExample();
                ce.createCriteria().andCustomer_nameEqualTo(o1.getCustomer_name());
                lc=cm.selectByExample(ce);
            }
            String tmp=o1.getItem_id();
            if(tmp.length()>0){
                String[] s=tmp.split(",");
                if(s.length>0){
                    for(String j:s){                    //统计总金额
                        oi=oim.selectByPrimaryKey(Integer.parseInt(j));
                        total+=oi.getTotal();
                        oi.setStatus("cancel");
                        oim.updateByPrimaryKeySelective(oi);
                    }
                }
            }
            if(lc!=null){
                for(Customer c:lc){

                    c.setUnused1(c.getUnused1()+total.intValue());
                    if(o1.getDes()!=null&&!o1.getDes().equals("")){                    //运费
                        c.setUnused1(c.getUnused1()+Integer.parseInt(o1.getDes()));
                    }
                    cm.updateByPrimaryKeySelective(c);
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

    @RequestMapping(value="/custom_name",method = RequestMethod.GET)
    @ResponseBody
    public EUDataGridResult custom_order(@RequestParam("customer_name") String custom_name){
        EUDataGridResult res=fs.getByCustomer_name(custom_name);
        Status status=new Status();
        status.setstatus(200);
        return res;
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
