package cn.kgc.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.ClassInfo;

public interface ClassMapper {
	ClassInfo getClassById(@Param("classId") Integer classId);

	List<ClassInfo>  getListClass(@Param("gradeId")Integer gradeId);
	
	List<ClassInfo>  toExamPlan(@Param("classId")Integer classId);

    List<ClassInfo> showListClass();

	ClassInfo getExamPlan(@Param("classId")Integer classId);

	ClassInfo findSelf(@Param("classId")Integer classId);
}
