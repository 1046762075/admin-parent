package com.firenay.leave.mapper;

import com.firenay.leave.entity.po.ProjectPO;
import com.firenay.leave.entity.po.ProjectPOExample;
import com.firenay.leave.entity.vo.DetailProjectVO;
import com.firenay.leave.entity.vo.PortalTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPOMapper {
    int countByExample(ProjectPOExample example);

    int deleteByExample(ProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPO record);

    int insertSelective(ProjectPO record);

    List<ProjectPO> selectByExample(ProjectPOExample example);

    ProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByExample(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByPrimaryKeySelective(ProjectPO record);

    int updateByPrimaryKey(ProjectPO record);

	/**
	 * 保存项目分类的关联信息
	 */
	void insertTypeRelationship(@Param("typeIdList") List<Integer> typeIdList,@Param("projectId") Integer projectId);

	/**
	 * 保存标签的关联关系信息
	 */
	void insertTagRelationship(@Param("tagIdList") List<Integer> tagIdList,@Param("projectId") Integer projectId);

	/**
	 * 查询所有众筹分类
	 */
	List<PortalTypeVO> selectPortalTypeVOs();

	/**
	 * 用户点击项目然后展示的内容
	 */
	DetailProjectVO selectDetailProjectVO(@Param("projectId") Integer projectId);
}