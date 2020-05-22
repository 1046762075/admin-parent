package com.firenay.boot.mapper;

import com.firenay.boot.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>Title: EmpMapper</p>
 * Description：
 * date：2020/5/17 11:58
 */
@Mapper
public interface EmpMapper {

	List<Emp> selectAll();
}
