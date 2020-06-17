package com.xr.controller;

import com.xr.entity.SysRole;
import com.xr.service.SysRoleService;
import com.xr.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@Api("角色信息接口")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/list")
    @RequiresPermissions("role:list")
    @ApiOperation(value = "获得角色信息",notes = "获得角色信息")
    public ResponseResult list(SysRole sysRole,Integer page,Integer limit){
        List<SysRole> list = sysRoleService.list(sysRole);
        ResponseResult result=new ResponseResult();
        result.getData().put("items",list);
        result.getData().put("total",list.size());
        return result;
    }
    @RequestMapping("/add")
    @RequiresPermissions("role:add")
    @ApiOperation(value = "添加角色信息",notes = "添加角色信息")
    public ResponseResult add(SysRole sysRole){
        sysRole.setDelFlag((byte) 0);
        sysRoleService.insert(sysRole);
        ResponseResult result=new ResponseResult();
        result.getData().put("message","添加成功");
        return result;
    }

    /**
     * 修改状态不删除
     * @return
     */
    @RequestMapping("/deleteZT")
/*
    @RequiresPermissions("role:deleteZT")
*/
    @ApiOperation(value = "删除角色信息",notes = "删除角色信息")
    public ResponseResult delete(SysRole sysRole){

        sysRoleService.deleteZT(sysRole);
        ResponseResult result=new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }
    @RequestMapping("/update")
    @RequiresPermissions("role:update")
    @ApiOperation(value = "修改角色信息",notes = "修改角色信息")
    public ResponseResult update(SysRole sysRole){

        sysRoleService.updateByPrimaryKey(sysRole);
        ResponseResult result=new ResponseResult();
        result.getData().put("message","修改成功");
        return result;
    }

}
