/*
package com.xr.controller;

import com.xr.entity.Users;
import com.xr.service.UsersService;
import com.xr.util.ResponseResult;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UsersController {
    //依赖注入业务类
    @Autowired
    private UsersService usersService;
    @RequestMapping("/login")
    public ResponseResult login(@RequestBody Users users,HttpSession session){
        Users loginUser = usersService.login(users);
        ResponseResult result=new ResponseResult();
        if(loginUser!=null){
            session.setAttribute("loginUser",loginUser);
            //登录成功 返回前端信息
            result.getData().put("message","登录成功");
            //标识
            result.getData().put("token",loginUser.getUsername());
        }else {
            //登录失败 返回前端信息
            result.getData().put("message","登录失败，用户或密码错误");
        }
        return result;
    }
//    public ResponseEntity<Map<String,Object>> login(@RequestBody Users users, HttpSession session){
//        Users loginUser=usersService.login(users);
//        Map<String,Object> map=new HashMap<>();
//        if(loginUser!=null){
//            //登录成功
//            map.put("msg","登录成功");
//            map.put("loginUser",loginUser);
//            System.out.println(loginUser);
//            map.put("token",session.getId());
//            return ResponseEntity.status(HttpStatus.OK).body(map);
//        }else{
//            //登录失败
//            map.put("msg","登录失败");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
//        }
//
//    }
}
*/
