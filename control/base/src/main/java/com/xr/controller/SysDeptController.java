package com.xr.controller;

import com.xr.entity.SysDept;
import com.xr.service.SysDeptService;
import com.xr.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 获取分组好的部门信息，供前台部门下拉框使用
     * @return
     */
    @RequestMapping("groupDept")
    public ResponseResult groupDept(){
        ResponseResult result = new ResponseResult();
        result.getData().put("deptList",sysDeptService.groupDept());
        return result;
    }
}
