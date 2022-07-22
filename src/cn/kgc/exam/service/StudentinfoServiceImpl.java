package cn.kgc.exam.service;

import java.util.List;

import cn.kgc.exam.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.pojo.ClassInfo;
import cn.kgc.exam.pojo.StudentInfo;

@Service
public class StudentinfoServiceImpl  implements StudentinfoService{

	@Autowired
	private StudentMapper stuMapper;

	@Override
	public StudentInfo receptionLogin(String studentAccount, String studentPwd) {
		
		return stuMapper.receptionLogin(studentAccount,studentPwd);
	}

	@Override
	public boolean addStudent(StudentInfo studentInfo) {
		int num = stuMapper.addStudent(studentInfo);
		if(num>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ClassInfo> listClass() {
		
		return stuMapper.listClass();
	}

	@Override
	public StudentInfo checkStuAccount(String studentAccount) {
		return stuMapper.checkStuAccount(studentAccount);
	}

	@Override
	public int getAllStudent() {
		return stuMapper.getAllStudent();
	}

	@Override
	public List<StudentInfo> listStudent() {

		return stuMapper.listStudent();
	}

	@Override
	public boolean deleteStudentById(Integer studentId) {
		int n = stuMapper.deleteStudentById(studentId);
		if (n > 0) {
			return true;
		}
		return false;
	}

	@Override
	public StudentInfo selectStudentById(Integer studentId) {


		return stuMapper.selectStudentById(studentId);
	}

	@Override
	public boolean updateStudent(StudentInfo studentInfo) {
		int n = stuMapper.updateStudent(studentInfo);
		if (n > 0) {
			return true;
		}
		return false;
	}

	@Override
	public StudentInfo findSelf(Integer studentId) {
		return stuMapper.findSelf(studentId);
	}

	@Override
	public boolean resetPWD(Integer studentId,String studentPwd) {
		int num = stuMapper.resetPWD(studentId,studentPwd);
		if (num>0){
			return true;
		}
		return false;
	}


	public StudentMapper getStuMapper() {
		return stuMapper;
	}

	public void setStuMapper(StudentMapper stuMapper) {
		this.stuMapper = stuMapper;
	}
}
