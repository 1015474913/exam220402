package cn.kgc.exam.mapper;

import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.TeacherInfo;

public interface TeacherMapper {

	TeacherInfo findTeacher(@Param("teacherAccount")String teacherAccount, @Param("teacherPwd")String teacherPwd);

	int getAllTeacher();

}
