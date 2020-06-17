package com.xr.service.impl;

import com.xr.entity.SysDept;
import com.xr.entity.SysDeptExample;
import com.xr.mapper.SysDeptMapper;
import com.xr.service.SysDeptService;
import com.xr.util.SysDeptGroupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Override
    public List<SysDept> groupDept() {
        // 查询出所有的部门信息
        SysDeptExample example = new SysDeptExample();
        SysDeptExample.Criteria criteria = example.createCriteria();
        // 查询所有状态为0的
        criteria.andDelFlagEqualTo(new Byte("0"));
        List<SysDept> list = sysDeptMapper.selectByExample(example);
        // 使用部门分组工具类来对所有部门进行分组
        SysDeptGroupUtil util = new SysDeptGroupUtil();
        list = util.getFirstDept(list);
        return list;
    }
}
