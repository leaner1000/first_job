package com.javan.controller;

import com.javan.dao.CancelItemMapper;
import com.javan.dao.CancelMapper;
import com.javan.entity.*;
import com.javan.service.CancelItemService;
import com.javan.service.CancelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CancelController {
    @Autowired
    private CancelService fs;
    @Autowired
    private CancelItemService ois;
    @Autowired
    private CancelItemMapper oim;
    @Autowired
    private CancelMapper om;

    @RequestMapping(value = "/cancel/page",method = RequestMethod.POST)
    @ResponseBody
    public EUDataGridResult CancelList(Integer page,Integer rows){
        return fs.getPage(page,rows);
    }

    @RequestMapping(value = "/cancel/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Cancel getCancel(@PathVariable Integer id){
        return fs.getByid(id);
    }

    @RequestMapping(value="/cancel/insert",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"cancel:add"})
    public Status insertCancel(Cancel f)  {
        Status s=new Status();
        om.insertSelective(f);
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/cancelitem/insert",method = RequestMethod.POST)     //取消订单中条目
    @ResponseBody
    @RequiresPermissions({"cancel:add"})
    public Status insertItem(@RequestBody CancelItem[] f)  {
        Status s=new Status();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<f.length;i++){
            oim.insertSelective(f[i]);
            if (i == 0) {
                sb.append(f[i].getCancelitem_id());
            }else {
                sb.append(","+f[i].getCancelitem_id());
            }
        }
        s.setstatus(200);
        s.setMsg(sb.toString());
        return s;
    }

    @RequestMapping(value="/cancel/delete_batch",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"cancel:cancel"})
    public Status delete(Integer[] ids){
        Cancel o=new Cancel();
        Cancel o1;
        CancelItem oi=new CancelItem();
        for(Integer i:ids){
            o1=om.selectByPrimaryKey(i);
            String tmp=o1.getItem_id();
            String[] s=tmp.split(",");
            if(s.length>0){
                for(String j:s){
                    oi.setCancelitem_id(Integer.parseInt(j));
                    oi.setStatus("cancel");
                    oim.updateByPrimaryKeySelective(oi);
                }
            }
            o.setCancel_id(i);
            o.setStatus("cancel");
            om.updateByPrimaryKeySelective(o);
        }
        Status s=new Status();
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/cancelitem/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<CancelItem> orederitem(@PathVariable Integer id){
        ArrayList<CancelItem> cancelItems=new ArrayList<CancelItem>();
        Cancel o=fs.getByid(id);
        String tmp=o.getItem_id();
        String[] s=tmp.split(",");
        for(String i:s){
            cancelItems.add(ois.getByid(Integer.parseInt(i)));
        }
        Status status=new Status();
        status.setstatus(200);
        return cancelItems;
    }

    @RequestMapping(value="/cancel/add",method = RequestMethod.GET)
    public String add(){
        return "cancel_add";
    }

    @RequestMapping(value="/cancel",method = RequestMethod.GET)
    public String cancel(){
        return "cancel";
    }
}
