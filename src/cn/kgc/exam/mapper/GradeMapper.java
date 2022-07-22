package cn.kgc.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.GradeInfo;

public interface GradeMapper {
	
	List<GradeInfo> getListGrade(@Param("gradeId") Integer gradeId);
	
	GradeInfo  showGrade(@Param("gradeId") Integer gradeId);

	List<GradeInfo> getGradeList();
}
