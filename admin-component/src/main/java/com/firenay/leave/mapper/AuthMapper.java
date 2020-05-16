package com.firenay.leave.mapper;

import com.firenay.leave.Auth;
import com.firenay.leave.AuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

	List<Integer> selectAssignedAuthIdByRoleId(Integer roleId);

	int deleteOldRelationship(Integer roleId);

	int insertNewRelationship(@Param("roleId") Integer roleId, @Param("authIds") List<Integer> authIds);

	List<String> selectAssignedAuthNameByAdminId(Integer adminId);
}