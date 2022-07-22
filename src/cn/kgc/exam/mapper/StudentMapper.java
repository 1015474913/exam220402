package cn.kgc.exam.mapper;

import cn.kgc.exam.pojo.ClassInfo;
import cn.kgc.exam.pojo.StudentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

	int getAllStudent();

	List<ClassInfo> listClass();

	StudentInfo receptionLogin(@Param("studentAccount")String studentAccount, @Param("studentPwd") String studentPwd);

	int addStudent(StudentInfo studentInfo);

	StudentInfo checkStuAccount(@Param("studentAccount") String studentAccount);

	List<StudentInfo> listStudent();

	int deleteStudentById(@Param("studentId") Integer studentId);

	StudentInfo selectStudentById(@Param("studentId") Integer studentId);

	int updateStudent(StudentInfo studentInfo);

    StudentInfo findSelf(@Param("studentId") Integer studentId);

    int resetPWD(@Param("studentId") Integer studentId,@Param("studentPwd") String studentPwd);
}
