package cn.kgc.exam.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cn.kgc.exam.pojo.ExamHistoryInfo;
import cn.kgc.exam.pojo.ExamPlanInfo;
import cn.kgc.exam.service.ExamHistoryService;
import cn.kgc.exam.service.ExamPaperService;
import cn.kgc.exam.service.ExamPlanService;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import cn.kgc.exam.pojo.ClassInfo;
import cn.kgc.exam.pojo.StudentInfo;
import cn.kgc.exam.service.StudentinfoService;

@Controller
@RequestMapping("/reception")
public class ReceptionController {
     @Autowired
     private StudentinfoService studentinfoService;
     @Autowired
    ExamPaperService examPaperService;
     @Autowired
    ExamPlanService examPlanService;
     @Autowired
     ExamHistoryService examHistoryService;
       
     @RequestMapping("/toIndex")
     public String toLogin() {

    	 return "reception/index";
     }

     @RequestMapping("/tologin")
     public String studentLogin() {
    	 return "reception/login";
     }
     
     
     @RequestMapping("/receptionLogin")
     public String receptionLogin(String studentAccount, String studentPwd, HttpSession session, Model model){
    	 System.out.println(studentAccount+","+studentPwd);
    	 
    	 //调用业务层
    	 StudentInfo studentInfo = studentinfoService.receptionLogin(studentAccount,studentPwd);
    	 if(studentInfo==null) {
    		 //model.addAttribute("error", "用户名或密码错误");
    		 return"redirect:/reception/tologin";
    	 }
    	 session.setAttribute("studentLogin", studentInfo);
    	 model.addAttribute("success","登陆成功");
		return "reception/indexsuc";
     }

     @RequestMapping(value="/checkStuAccount",method = RequestMethod.POST)
     public void checkStuAccount(String studentAccount,HttpServletResponse res) throws IOException {
         StudentInfo student = studentinfoService.checkStuAccount(studentAccount);
        if (student==null){
            res.getWriter().print("n");
        }else{
            res.getWriter().print(student.getStudentPwd());
        }

     }

     @RequestMapping("/exit")
     public String exit(HttpSession httpSession){
         StudentInfo studentInfo = (StudentInfo) httpSession.getAttribute("studentLogin");
         httpSession.removeAttribute("studentLogin");
         return "redirect:/reception/toIndex";
     }

     @RequestMapping("/home")
     public String receptio() {
    
     return "reception/home";
    	 
     }
     
     
     
     @RequestMapping("/toaddStudent")
     public String register(Model model) {
    	 List<ClassInfo> listClass = studentinfoService.listClass();
    	 model.addAttribute("classs",listClass);
		return "reception/register";
    	 
     }
     
     @RequestMapping(value = "/addStudent.json")
     @ResponseBody
     public String addStudent(StudentInfo studentInfo,HttpSession session,HttpServletRequest req) {
		 boolean flag = studentinfoService.addStudent(studentInfo);
		 String jsonString = null;
		 JSONObject json = new JSONObject();
		
		 if(flag) {
			 json.put("success", flag);			 
		 }
		 jsonString = json.toJSONString();
		 return jsonString;
    	      
     }
     @RequestMapping("/self")
    public String findSelf(Integer studentId,Model model){
         StudentInfo student = studentinfoService.findSelf(studentId);
         if (student!=null){
             model.addAttribute("self",student);
             return "reception/self";
         }
         model.addAttribute("error","查看失败");
         return "reception/indexsuc";
     }
     @RequestMapping("/reset")
    public void resetPWD(Integer studentId,String studentPwd, HttpServletResponse res) throws IOException {
         boolean flag = studentinfoService.resetPWD(studentId, studentPwd);
         if (flag) {
             res.getWriter().print("t");
         } else {
             res.getWriter().print("f");
         }
     }
     @RequestMapping("/willexams")
    public String willexams(Integer classId,Integer gradeId,Integer studentId,Model model){
        List<ExamPlanInfo> examPlans = examPlanService.getExamPlanList();
            model.addAttribute("examPlans", examPlans);
            return "reception/examCenter";
     }
     @RequestMapping("/history")
    public String history(Integer studentId){
         List<ExamHistoryInfo> examHistorys = examHistoryService.getAllExamHistory(studentId);



        return "";
     }
}
     
