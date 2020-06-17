package com.xr.service.impl;

import com.xr.entity.SysUser;
import com.xr.entity.SysUserExample;
import com.xr.mapper.SysUserMapper;
import com.xr.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Set<String> listPermissions(String name) {
        return sysUserMapper.listPermissions(name);
    }

    @Override
    public void add(SysUser sysUser) {
        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public void deleteById(Long id) {
        sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public List<SysUser> list(SysUser sysUser) {
        SysUserExample example = new SysUserExample();//创建查询对象
        SysUserExample.Criteria criteria = example.createCriteria();//创建查询的条件
        if(sysUser!=null){
            if(sysUser.getUsername()!=null){
                criteria.andUsernameLike("%"+sysUser.getUsername()+"%");//传参查询
            }// 还可以添加其他属性的条件

        }
        List<SysUser> list =  sysUserMapper.selectByExample(example);
        return list;
    }

    @Override
    public SysUser login(SysUser sysUser) {
        // 构建自定义查询条件
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(sysUser.getUsername());
        criteria.andPasswordEqualTo(sysUser.getPassword());
        List<SysUser> list =  sysUserMapper.selectByExample(example);
        if(list!=null && list.size()==1){
            return list.get(0);
        }
        return  null;
    }

    @Override
    public SysUser findUserByUserName(String username) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SysUser> list =  sysUserMapper.selectByExample(example);
        if(list!=null && list.size()==1){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<String> findUserRoles(String username) {
        List<String> roles = sysUserMapper.findUserRoles(username);
        return roles;
    }
}
