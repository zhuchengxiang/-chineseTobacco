package com.xr.service;

import com.xr.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    int deleteZT(SysRole sysRole);

    public List<SysRole> list(SysRole sysRole);

    int insert(SysRole sysRole);

}
