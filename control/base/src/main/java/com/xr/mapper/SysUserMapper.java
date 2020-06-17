package com.xr.mapper;

import com.xr.entity.SysUser;
import com.xr.entity.SysUserExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper {
    /**
     * 根据用户名查询角色集合
     * @param username
     * @return
     */

    @Select("SELECT m.perms from sys_user u \n" +
            "INNER JOIN sys_user_role ur on u.id=ur.id\n" +
            "INNER JOIN sys_role r on r.id=ur.id\n" +
            "INNER JOIN sys_role_menu rm on rm.role_id=r.id\n" +
            "INNER JOIN sys_menu m on m.id=rm.menu_id where u.username=#{username}")
    Set<String> listPermissions(String name);

    public List<String> findUserRoles(String username);
    long countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}
