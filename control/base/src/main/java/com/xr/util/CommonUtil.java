package com.xr.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

public class CommonUtil {
    private CommonUtil(){}

    /**
     * 产生盐值
     * @return
     */
    public static String getSalt(){
        //生成盐需要存入数据库中
        String salt=new SecureRandomNumberGenerator().nextBytes().toHex();
        return salt;
    }

    /**
     *  根据盐值和原始密码MD5加密n次
     * @param originalPassword
     * @param salt
     * @param n
     * @return
     */
    public static String getMD5Password(String originalPassword,String salt,Integer n){
        String md5Password=new Md5Hash(originalPassword,salt,n).toString();
        return md5Password;
    }

    //测试
    public static void main(String[] arge){
        // 生成盐（部分 需要存入数据库中
        String salt=getSalt();
        System.out.println(salt);
        //将原始密码加盐 ，并且md5加密三次， 最后加入数据库
        String pwd=getMD5Password("admin",salt,2);
        System.out.println(pwd);
    }
    /**
     * 判断对象或字符串是否为空
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj){
        if (obj ==null ){
            return false;
        }
        if(obj instanceof String){
            if(((String) obj).trim().equals("")){
                return false;
            }
        }
        return true;
    }
}
