package com.javan.controller;

import com.javan.dao.ClothMapper;
import com.javan.dao.SpecialPriceMapper;
import com.javan.entity.*;
import com.javan.service.ClothService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;


@Controller
public class ClothController {
        @Autowired
        private ClothService fs;
        @Autowired
        private ClothMapper cm;
        @Autowired
        private SpecialPriceMapper spm;


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

        @RequestMapping(value = "/cloth/autocomplete",method = RequestMethod.GET)
        @ResponseBody
        public List<?> autoCompleteCloth(@Param("q") String q){
                if(q==null||q.length()==0){
                        return fs.getPage(1,100).getRows();
                }
                return cm.autoComplete(q);
        }

        @RequestMapping(value = "/cloth/default_price",method = RequestMethod.GET)
        @ResponseBody
        public Status getDefaultPrice(@Param("cloth_id")Integer cloth_id,@Param("customer_name")String custom_name){
            SpecialPriceKey spk = new SpecialPriceKey();
            spk.setCloth_id(cloth_id);
            spk.setCustom_id(custom_name);
            SpecialPrice sp = spm.selectByPrimaryKey(spk);
            Status s=new Status();
            if(sp==null){
                s.setstatus(500);
                s.setMsg("没有特殊价");
                return s;
            }
            s.setstatus(200);
            s.setMsg(sp.getDefault_price().toString());
            return s;
        }

        @RequestMapping(value="/cloth/insert",method = RequestMethod.POST)
        @ResponseBody
        @RequiresPermissions({"cloth:add"})
        public Status insert(@RequestPart("picture_path") Part picture, Cloth f, BindingResult bindingResult, HttpServletRequest request)  {
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
                        try {
                                f.setPicture_path(""+System.currentTimeMillis()+".jpg");
                                String upload_path=request.getSession().getServletContext().getRealPath("")+"/WEB-INF/img/"+f.getPicture_path();  //图片上传路径
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
