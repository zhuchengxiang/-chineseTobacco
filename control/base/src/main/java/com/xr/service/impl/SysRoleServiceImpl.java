package com.xr.service.impl;

import com.xr.entity.SysRole;
import com.xr.entity.SysRoleExample;
import com.xr.mapper.SysRoleMapper;
import com.xr.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        return sysRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteZT(SysRole sysRole) {
        return sysRoleMapper.deleteZT(sysRole);
    }

    @Override
    public List<SysRole> list(SysRole sysRole) {
        SysRoleExample example=new SysRoleExample();
        SysRoleExample.Criteria criteria=example.createCriteria();//创建查询条件、、
        if(sysRole!=null){
            if(sysRole.getName()!=null){
                criteria.andNameLike("%"+sysRole.getName()+"%");//传参查询
            }// 还可以添加其他属性的条件
            criteria.andDelFlagEqualTo((byte) 0);
        }
        List<SysRole> list = sysRoleMapper.selectByExample(example);
        return list;

    }

    @Override
    public int insert(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }
}
