package com.javan.shiro;

import com.javan.entity.SystemUser;
import com.javan.service.SystemPermissionService;
import com.javan.service.SystemRoleService;
import com.javan.service.SystemUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RealmImpl extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemUserService userser;

    @Autowired
    private SystemPermissionService sysper;

    @Autowired
    private SystemRoleService sysrole;

    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username=(String) token.getPrincipal();
        SystemUser su=null;
        try{
            su= userser.getByName(username);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        if(su==null){
            if (logger.isDebugEnabled()){
                logger.debug("user not exist!");
            }
            return null;
        }
        String password=su.getPassword();
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(su,password,this.getName());
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SystemUser su=(SystemUser)principals.getPrimaryPrincipal();
        int roleid=su.getRole_id();
        ArrayList<Integer> per_list=sysrole.getPermssion(roleid);
        List<String> permissions = new ArrayList<String>();
        for(int per_id:per_list){
            permissions.add(sysper.getPermissionById(per_id));
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }
}
