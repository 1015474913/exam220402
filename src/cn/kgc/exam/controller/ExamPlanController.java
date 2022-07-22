package cn.kgc.exam.controller;

import java.util.Date;
import java.util.List;

import cn.kgc.exam.pojo.ClassInfo;
import cn.kgc.exam.pojo.CourseInfo;
import cn.kgc.exam.pojo.ExamPaperInfo;
import cn.kgc.exam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kgc.exam.pojo.ExamPlanInfo;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/edu/examplan")
public class ExamPlanController {
	
	@Autowired
	private ExamPlanService examPlanService;
	@Autowired
    private ClassService classService;
	@Autowired
    private CourseService courseService;
	@Autowired
    private ExamPaperService examPaperService;
	
	@RequestMapping("/examPlans")
	public String examPlans(Model model){
		List<ExamPlanInfo> examPlan = examPlanService.getExamPlanList();
		if (examPlan!=null) {
			model.addAttribute("examPlans", examPlan);
			return "admin/examPlans";
		}else{
			model.addAttribute("error", "系统繁忙");
			return "error";
		}
	}
	@RequestMapping("/preAddep")
	public String preAddep(@RequestParam Integer examPlanId, Model model){
        ExamPlanInfo examPlanInfo = examPlanService.getExamPlanById(examPlanId);
	    List<ClassInfo> classes = classService.getClassList();
	    List<CourseInfo> courses = courseService.getCourseList();
	    List<ExamPaperInfo> examPapers = examPaperService.getExamPapersList();
	    model.addAttribute("classes",classes);
        model.addAttribute("courses",courses);
        model.addAttribute("examPapers",examPapers);
        model.addAttribute("examPlan",examPlanInfo);
		return "admin/examPlanedit";
    }
    @RequestMapping("/addExamPlan")
    public String addExamPlan(@RequestParam Integer classId,
                              @RequestParam Integer courseId,
                              @RequestParam Integer examPaperId,
                              @RequestParam String beginTime,
                              Model model){
        //System.out.printf(classId+","+courseId+","+examPaperId);
        boolean flag = examPlanService.addExamPlan(classId,courseId,examPaperId,beginTime);
        if (flag) {
            return "redirect:/edu/examplan/examPlans";
        }
        model.addAttribute("error","系统繁忙");
        return "error";
    }
    @RequestMapping("/delExamPlan")
	public String delExamPlan(Integer examPlanId,Model model){
		boolean flag = examPlanService.deleteExamPlan(examPlanId);
		if (flag) {
			return "redirect:/edu/examplan/examPlans";
		}
		model.addAttribute("error","系统繁忙");
		return "error";
	}
	@RequestMapping("/preUpdateep")
	public String preUpdateep(Integer examPlanId, Model model){
		ExamPlanInfo examPlanInfo = examPlanService.showExamPlanById(examPlanId);
		List<ClassInfo> classes = classService.getClassList();
		List<CourseInfo> courses = courseService.getCourseList();
		List<ExamPaperInfo> examPapers = examPaperService.getExamPapersList();
		if (examPlanInfo!=null){
			model.addAttribute("classes",classes);
			model.addAttribute("courses",courses);
			model.addAttribute("examPapers",examPapers);
			model.addAttribute("examPlan",examPlanInfo);
			return "admin/examPlanedit";
		}
		model.addAttribute("error","系统繁忙");
		return "error";
	}
}
