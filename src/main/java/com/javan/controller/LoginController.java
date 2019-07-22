package com.javan.controller;

import com.javan.entity.Status;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public Status Login(@RequestParam String username, @RequestParam String password, HttpSession session){
        Status s = new Status();
        s.setstatus(200);
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try{
                currentUser.login(token);
            }catch(UnknownAccountException ex){
                s.setMsg("用户不存在");
                s.setstatus(400);
            }catch(IncorrectCredentialsException ex){
                s.setMsg("密码错误");
                s.setstatus(400);
            }catch(AuthenticationException ex){
                s.setMsg("没有权限");
                s.setstatus(400);
            }
        }
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/logout",method = {RequestMethod.GET,RequestMethod.POST})
    public Status logout(){
        Status s=new Status();
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        s.setstatus(200);
        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/checklogin",method = {RequestMethod.GET,RequestMethod.POST})
    public boolean checklogin(){
        Status s=new Status();
        Subject subject=SecurityUtils.getSubject();
        return subject.isAuthenticated();
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String home(){
        return "home";
    }

}
