package com.javan.controller;

import com.javan.entity.EUDataGridResult;
import com.javan.entity.Cloth;
import com.javan.entity.Status;
import com.javan.service.ClothService;
import com.javan.service.OrderItemService;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


@Controller
public class ClothController {
        @Autowired
        private ClothService fs;


        @RequestMapping(value = "/cloth/page",method = RequestMethod.POST)
        @ResponseBody
        public EUDataGridResult ClothList(Integer page,Integer rows){
            return fs.getPage(page,rows);
        }

        @RequestMapping(value = "/cloth/{id}",method = RequestMethod.GET)
        @ResponseBody
        public Cloth getCloth(@PathVariable Integer id){
            return fs.getByid(id);
        }

        @RequestMapping(value="/cloth/insert",method = RequestMethod.POST)
        @ResponseBody
        @RequiresPermissions({"cloth:add"})
        public Status insert(@RequestPart("picture_path") Part picture,Cloth f,BindingResult bindingResult,HttpServletRequest request)  {
                Status s=new Status();
                f.setPicture_path(""+System.currentTimeMillis()+".jpg");
                String upload_path=request.getSession().getServletContext().getRealPath("")+"/WEB-INF/img/"+f.getPicture_path();  //图片上传路径
                f.setPicture_path("/img/"+f.getPicture_path());
                try {
                        picture.write(upload_path);
                } catch (IOException e) {
                        s.setstatus(400);
                        s.setMsg("上传失败");
                        return s;
                }
                if(bindingResult.hasErrors()){
                        FieldError fieldError = bindingResult.getFieldError();
                        s.setMsg(fieldError.getDefaultMessage());
                        s.setstatus(400);
                        return s;
                }
                if(fs.getByid(f.getCloth_id())!=null){
                        s.setstatus(400);
                        s.setMsg("编号重复");
                        return s;
                }
                fs.insert(f);
                s.setstatus(200);
                return s;
        }

        @RequestMapping(value="/cloth/update",method = RequestMethod.POST)
        @ResponseBody
        @RequiresPermissions({"cloth:edit"})
        public Status update(@RequestPart("picture_path") Part picture,Cloth f,BindingResult bindingResult,HttpServletRequest request){
                Status s = new Status();
                if(picture.getSubmittedFileName()!=null) {
                        f.setPicture_path("/img/" + picture.getSubmittedFileName());
                        try {
                                String upload_path = request.getSession().getServletContext().getRealPath("") + "WEB-INF\\img\\" + picture.getSubmittedFileName();
                                picture.write(upload_path);
                        } catch (IOException e) {
                                s.setstatus(400);
                                s.setMsg("上传失败");
                                return s;
                        }
                        f.setPicture_path("/img/" + picture.getSubmittedFileName());
                }else{
                        f.setPicture_path(null);
                }
                fs.updata(f);
                s.setstatus(200);
                return s;
        }
        @RequestMapping(value="/cloth/delete_batch",method = RequestMethod.POST)
        @ResponseBody
        @RequiresPermissions({"cloth:delete"})
        public Status delete(Integer[] ids){
                fs.delete_batch(ids);
                Status s=new Status();
                s.setstatus(200);
                return s;
        }

        @RequestMapping(value="/cloth/add",method = RequestMethod.GET)
        public String add(){
                return "cloth_add";
        }

        @RequestMapping(value="/cloth",method = RequestMethod.GET)
        public String cloth(){
                return "cloth";
        }

        @RequestMapping(value="/cloth/edit",method = RequestMethod.GET)
        public String edit(){
                return "cloth_edit";
        }


}
