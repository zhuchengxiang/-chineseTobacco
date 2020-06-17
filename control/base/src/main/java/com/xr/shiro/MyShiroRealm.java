package com.xr.shiro;

import com.xr.entity.SysMenu;
import com.xr.entity.SysRole;
import com.xr.entity.SysUser;
import com.xr.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

//自定义域


public class MyShiroRealm extends AuthorizingRealm {

    @Override
    public String getName(){
        return "myShiroRealm";
    }
    @Autowired
    private SysUserService sysUserService;

@Override
/**
 * 授权
 * doGetAuthorizationInfo方法是在我们调用
 * SecurityUtils.getSubject().isPermitted（）这个方法时触发，
 * 开启了注解就是进入有 @RequiresPermissions或@RequiresRoles() 两个注解时触发
 * 授权后用户角色及权限会保存在缓存中的
 *
 * @param principal
 * @return
 * @RequiresPermissions这个注解起始就是在执行SecurityUtils.getSubject().isPermitted（）
 */
protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    // 从session中获取 user 对象
    Session session = SecurityUtils.getSubject().getSession();
    SysUser sysUser = (SysUser) session.getAttribute("USER_SESSION");
    // 查到权限数据，返回授权信息，要包括上面的权限和角色(可选，一般不这么用了)
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    List<String> roles = sysUserService.findUserRoles(sysUser.getUsername());
    for (String role : roles) {
        if (role!=""&&role!=null){
            simpleAuthorizationInfo.addRole(role);
        }
    }
    Set<String> permissions = sysUserService.listPermissions(sysUser.getUsername());
    for (String permission : permissions) {
        if (permission!=null&&permission!=""){
            simpleAuthorizationInfo.addStringPermission(permission);
        }
    }
    return simpleAuthorizationInfo;

}

    /*    *
     * 认证 登录
     * doGetAuthenticationInfo这个方法是在用户登录的时候
     * 也就是执行SecurityUtils.getSubject().login()是调用（及登录
     * 验证通过后悔用户保存在缓存中
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try{
            //获取用户的输入账户
            String userName=(String) token.getPrincipal();
            //通过userName从数据中查找user对象 如果找到。没找到实际项目中
            // 这里可以根据实际情况做缓存 如果不做shrio自己也是有时两分钟不会重复执行该方法
            SysUser sysUser=sysUserService.findUserByUserName(userName);
            if(sysUser == null){
                return null;
            }
        /*    if(sysUser.getSalt() == 0){// 冻结账户
                throw new LockedAccountException();
            }*/
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    userName, //用户名
                    sysUser.getPassword(), //密码
                    ByteSource.Util.bytes(sysUser.getSalt()),//salt=username+salt密码加盐
                    getName()  // 当前 realm对象的name.调用父类的getName()方法即可
            );
            //将用户信息存入session、
            Session session=SecurityUtils.getSubject().getSession();
            session.setAttribute("USER_SESSION",sysUser);
            return authenticationInfo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
