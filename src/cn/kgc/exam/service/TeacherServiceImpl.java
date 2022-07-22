package cn.kgc.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.TeacherMapper;
import cn.kgc.exam.pojo.TeacherInfo;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherMapper tMapper;
	
	@Override
	public TeacherInfo findTeacher(String teacherAccount, String teacherPwd) {
		
		return tMapper.findTeacher(teacherAccount,teacherPwd);
	}

	@Override
	public int getAllTeacher() {
		
		return tMapper.getAllTeacher();
	}

}
