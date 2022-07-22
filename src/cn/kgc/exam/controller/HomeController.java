package cn.kgc.exam.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import cn.kgc.exam.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/edu/home")
public class HomeController {
	
	@Autowired
	private ExamPaperService examPaperService;
	@Autowired
	private StudentinfoService studentService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TeacherService teacherService;
	
	private Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/homeInfo")
	public void homeInfo(HttpServletResponse response) throws IOException {
		logger.info("���غ�̨��ҳ�������");
		int allExamPapper = examPaperService.getAllExamPaper();
		int allStudent = studentService.getAllStudent();
		int allSubject = subjectService.getAllSubject();
		int  allTeacher = teacherService.getAllTeacher();
		String json = "{\"examPaperTotal\":" + allExamPapper + ", " + "\"subjectTotal\":" + allSubject + ", "
				+ "\"teacherTotal\":" + allTeacher + ", " + "\"studentTotal\":" + allStudent + "}";
		
		System.out.println(json);
		response.getWriter().print(json);
	}
}
