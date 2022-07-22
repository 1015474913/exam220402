package cn.kgc.exam.service;

import cn.kgc.exam.pojo.TeacherInfo;

public interface TeacherService {

	TeacherInfo findTeacher(String teacherAccount, String teacherPwd);

	int getAllTeacher();

}
