package com.javan.controller;

import com.javan.dao.ClothMapper;
import com.javan.dao.OutstockinfoMapper;
import com.javan.entity.*;
import com.javan.service.OutstockinfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

@Controller
public class OutStockController {
    @Autowired
    private OutstockinfoService ob;
    @Autowired
    private OutstockinfoMapper om;
    @Autowired
    private ClothMapper cm;

    @RequestMapping(value = "/outstock/page",method = RequestMethod.POST)
    @ResponseBody
    public EUDataGridResult OutstockList(Integer page,Integer rows){
        return ob.getPage(page,rows);
    }

    @RequestMapping(value = "/outstock/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Outstockinfo getOB(@PathVariable Integer id){
        return ob.getByid(id);
    }

//    @RequestMapping(value = "/outstock/autocomplete",method = {RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody
//    public List<?> autoComplete(@Param("q") String q){
//        if(q==null||q.length()==0){
//            return ob.getPage(1,100).getRows();
//        }
//        return om.autoComplete(q);
//    }

    @RequestMapping(value="/outstock/insert",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"outstock:add"})
    public Status insert(@RequestPart("picture_path") Part picture,@RequestPart("des") Part des, Outstockinfo f, BindingResult bindingResult, HttpServletRequest request){
        Status s=new Status();
        String upload_path="";
        if(des.getSubmittedFileName()!=null) {
            try {
                f.setDes(""+System.currentTimeMillis()+".mp3");
                upload_path=request.getSession().getServletContext().getRealPath("")+"/WEB-INF/audio/"+f.getDes();  //图片上传路径
                f.setDes("/audio/"+f.getDes());
                des.write(upload_path);
            } catch (IOException e) {
                s.setstatus(400);
                s.setMsg("上传失败"+upload_path);
                return s;
            }
        }else{
            f.setDes(null);
        }
        if(picture.getSubmittedFileName()!=null) {
            try {
                f.setPicture_path(""+System.currentTimeMillis()+".jpg");
                upload_path=request.getSession().getServletContext().getRealPath("")+"/WEB-INF/img/"+f.getPicture_path();  //图片上传路径
                f.setPicture_path("/img/"+f.getPicture_path());
                picture.write(upload_path);
            } catch (IOException e) {
                s.setstatus(400);
                s.setMsg("上传失败");
                return s;
            }
        }else{
            f.setPicture_path(null);
        }
        if(f.getPicture_path()==null){
            Cloth c=cm.selectByPrimaryKey(f.getCloth_id());
            if(c!=null){
                f.setPicture_path(c.getPicture_path());
            }
        }
        om.insertSelective(f);
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/outstock/update",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"outstock:edit"})
    public Status update(@RequestPart("picture_path") Part picture,@RequestPart("des") Part des, Outstockinfo f, BindingResult bindingResult, HttpServletRequest request){
        Status s=new Status();
        String upload_path="";
        if(des.getSubmittedFileName()!=null) {
            try {
                f.setDes(""+System.currentTimeMillis()+".mp3");
                upload_path=request.getSession().getServletContext().getRealPath("")+"/WEB-INF/audio/"+f.getDes();  //图片上传路径
                f.setDes("/audio/"+f.getDes());
                des.write(upload_path);
            } catch (IOException e) {
                s.setstatus(400);
                s.setMsg("上传失败");
                return s;
            }
        }else{
            f.setDes(null);
        }
        if(picture.getSubmittedFileName()!=null) {
            try {
                f.setPicture_path(""+System.currentTimeMillis()+".jpg");
                upload_path=request.getSession().getServletContext().getRealPath("")+"/WEB-INF/img/"+f.getPicture_path();  //图片上传路径
                f.setPicture_path("/img/"+f.getPicture_path());
                picture.write(upload_path);
            } catch (IOException e) {
                s.setstatus(400);
                s.setMsg("上传失败");
                return s;
            }
        }else{
            f.setPicture_path(null);
        }
        if(f.getPicture_path()==null){
            Cloth c=cm.selectByPrimaryKey(f.getCloth_id());
            if(c==null){
                f.setPicture_path(c.getPicture_path());
            }
        }
        om.insertSelective(f);
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/outstock/delete_batch",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions({"outstock:delete"})
    public Status delete(Integer[] ids){
        ob.delete_batch(ids);
        Status s=new Status();
        s.setstatus(200);
        return s;
    }

    @RequestMapping(value="/outstock/add",method = RequestMethod.GET)
    public String add(){
        return "outstock_add";
    }

    @RequestMapping(value="/outstock",method = RequestMethod.GET)
    public String furniture(){
        return "outstock";
    }

    @RequestMapping(value="/outstock/edit",method = RequestMethod.GET)
    public String edit(){
        return "outstock_edit";
    }
}
