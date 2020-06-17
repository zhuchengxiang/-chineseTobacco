package com.xr.service;

import com.xr.entity.SysDept;

import java.util.List;

public interface SysDeptService {
    /**
     * 查询分组的部门信息
     * @return
     */
    public List<SysDept> groupDept();
}
