package com.xr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SysUser{
    //角色集合
    private List<SysRole> roles=new ArrayList<>();
    private Long id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Byte status;
    private Long deptId;
    private String createBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前台传过来的日期格式转换
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 后台传到前台的日期格式转换
    private Date createTime;
    private String lastUpdateBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前台传过来的日期格式转换
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;
    private Byte delFlag;
    private String introduction;
    private String avatar;
    private static final long serialVersionUID = 1L;
}
