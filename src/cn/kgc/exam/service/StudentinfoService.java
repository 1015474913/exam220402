package cn.kgc.exam.service;



import java.util.List;

import cn.kgc.exam.pojo.ClassInfo;
import cn.kgc.exam.pojo.StudentInfo;

public interface StudentinfoService {

	StudentInfo receptionLogin(String studentAccount, String studentPwd);

	boolean addStudent(StudentInfo studentInfo);

	List<ClassInfo> listClass();

	StudentInfo checkStuAccount(String studentAccount);

	int getAllStudent();

	List<StudentInfo> listStudent();

	boolean deleteStudentById(Integer studentId);

	StudentInfo selectStudentById(Integer studentId);

	boolean updateStudent(StudentInfo studentInfo);

    StudentInfo findSelf(Integer studentId);

    boolean resetPWD(Integer studentId,String studentPwd);
}
