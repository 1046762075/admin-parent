package com.firenay.leave.mapper;

import com.firenay.leave.Role;
import com.firenay.leave.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
	List<Role> selectRoleByKeyword(String keyword);

    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(@Param("roleId")Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	/**
	 * 自定义mapper查询已分配的
	 */
	List<Role> selectAssignedRole(Integer adminId);

	/**
	 * 自定义mapper查询未分配的
	 */
	List<Role> selectUnAssignedRole(Integer adminId);

	int saveinnerRoleAuth(@Param("roleId") Integer roleId, @Param("authId")int authId);

}