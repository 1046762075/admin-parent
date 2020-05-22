package com.firenay.leave.mapper;

import com.firenay.leave.entity.po.ProjectItemPicPO;
import com.firenay.leave.entity.po.ProjectItemPicPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectItemPicPOMapper {
    int countByExample(ProjectItemPicPOExample example);

    int deleteByExample(ProjectItemPicPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectItemPicPO record);

    int insertSelective(ProjectItemPicPO record);

    List<ProjectItemPicPO> selectByExample(ProjectItemPicPOExample example);

    ProjectItemPicPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectItemPicPO record, @Param("example") ProjectItemPicPOExample example);

    int updateByExample(@Param("record") ProjectItemPicPO record, @Param("example") ProjectItemPicPOExample example);

    int updateByPrimaryKeySelective(ProjectItemPicPO record);

    int updateByPrimaryKey(ProjectItemPicPO record);

	/**
	 * 保存发起众筹上传的所有图片的路径
	 */
	void insertPaths(@Param("detailPicturePaths") List<String> detailPicturePaths, @Param("projectId") Integer projectId);
}