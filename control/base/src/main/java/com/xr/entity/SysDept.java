package com.xr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class SysDept implements Serializable {
    // 部门下的部门子集
    private List<SysDept> items = new ArrayList<>();
    private Long id;
    private String name;
    private Long parentId;
    private Integer orderNum;
    private String createBy;
    private Date createTime;
    private String lastUpdateBy;
    private Date lastUpdateTime;
    private Byte delFlag;
    private static final long serialVersionUID = 1L;

}
