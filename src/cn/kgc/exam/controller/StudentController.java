package cn.kgc.exam.controller;

import java.util.List;

import cn.kgc.exam.service.ClassService;
import cn.kgc.exam.service.StudentinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.kgc.exam.pojo.ClassInfo;
import cn.kgc.exam.pojo.StudentInfo;

@RequestMapping("/edu/admin")
@Controller
public class StudentController {
	@Autowired
	StudentinfoService studentinfoService;

	@Autowired
	ClassService classService;

	@RequestMapping("/tostudents")
	public String listStudent(Model model, Integer startPage) {
		if (startPage == null) {
			startPage = 1;
		}
		PageHelper.startPage(startPage, 8);
		List<StudentInfo> list = studentinfoService.listStudent();
		model.addAttribute("students", list);

		PageInfo<StudentInfo> pageInfo = new PageInfo<StudentInfo>(list);
		model.addAttribute("pageInfo", pageInfo);

		model.addAttribute("pageNow", startPage);// ��ǰҳ
		model.addAttribute("pageTotal", pageInfo.getPages());// ��ҳ��

		return "admin/students";
	}

	@RequestMapping("/deleteStudent")
	public String deleteStudent(Integer studentId) {
		boolean flag = studentinfoService.deleteStudentById(studentId);
		if (flag) {
			return "redirect:/edu/admin/tostudents";
		}
		return "error";
	}

	@RequestMapping("/selectStudentById")
	public String selectStudentById(Integer studentId, Model model) {
		StudentInfo stu = studentinfoService.selectStudentById(studentId);
		model.addAttribute("student", stu);

		List<ClassInfo> list = classService.getClassList();
		model.addAttribute("classes", list);
		return "admin/studentedit";
	}

	@RequestMapping("/updateStudent")
	public String updateStudent(StudentInfo studentInfo) {
		System.out.println(1);
		System.out.println(studentInfo);
		boolean flag = studentinfoService.updateStudent(studentInfo);

		if (flag) {
			return "redirect:/edu/admin/tostudents";
		}
		return "error";
	}

	@RequestMapping("/StudentsCount")
	public String StudentsCount() {
		return "admin/charts/studentExamCount";
	}

}
