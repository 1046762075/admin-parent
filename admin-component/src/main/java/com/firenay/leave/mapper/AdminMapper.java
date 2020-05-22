package com.firenay.leave.mapper;

import com.firenay.leave.AdminExample;
import com.firenay.leave.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
	List<Admin> selectAdminByKeyword(String keyword);

    int countByExample(AdminExample example);
    
    /**
     *  给添加的用户赋普通用户
     */
    int saveInnerAdminRole(@Param("adminId")Integer adminId);
    
    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

	int deleteRelationship(Integer adminId);

	int saveRelationship(@Param("adminId") Integer adminId, @Param("roleIds") List<Integer> roleIds);

	/**
	 * 移除用户的权限
	 */
	int removeInnerAdminRole(@Param("adminId") Integer adminId);
}