package cn.kgc.exam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kgc.exam.pojo.TeacherInfo;
import cn.kgc.exam.service.TeacherService;

@Controller
@RequestMapping("/edu")
public class AdminController {
	@Autowired
	private TeacherService tService ;
	
	@RequestMapping("admin/toLogin")
	public String toLogin(){
			
		return "admin/login";
	}
	
	@RequestMapping("/toIndex")
	public String  toIndex(String teacherAccount,String teacherPwd,Model model,HttpSession session){
		TeacherInfo tInfo = tService.findTeacher(teacherAccount,teacherPwd);
		session.setAttribute("admin", tInfo);
		if (tInfo!=null) {
			return "admin/index";
		}else{
	    model.addAttribute("error", "账号或密码错误");
		return "admin/login";
		}
	}
	
	@RequestMapping("/exitTeacher") 
	public  String  exitTeacher(HttpSession hSession){
		hSession.removeAttribute("admin");
		
		return "admin/login";	
	}
	
	
	
	
}
