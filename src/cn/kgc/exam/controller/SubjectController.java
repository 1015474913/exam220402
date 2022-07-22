package cn.kgc.exam.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sound.midi.MidiDevice.Info;
import javax.websocket.Session;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.kgc.exam.pojo.CourseInfo;
import cn.kgc.exam.pojo.ExamPaperInfo;
import cn.kgc.exam.pojo.ExamSubjectMiddleInfo;
import cn.kgc.exam.pojo.GradeInfo;
import cn.kgc.exam.pojo.SubjectInfo;
import cn.kgc.exam.service.CourseService;
import cn.kgc.exam.service.ESMService;
import cn.kgc.exam.service.ExamPaperService;
import cn.kgc.exam.service.GradeService;
import cn.kgc.exam.service.SubjectService;
import cn.kgc.exam.util.SubjectImportUtil;

@Controller
@RequestMapping("/edu/subject")
public class SubjectController {
	
	@Autowired
	private SubjectService sService;
	@Autowired
	private CourseService cService;
	@Autowired
	private GradeService gService;
	@Autowired
	private ExamPaperService epService;
	@Autowired
	private ESMService esmService;
	@Autowired
	private ExamPaperService examPaperService;
	
	private ExamPaperInfo examPaper;
	private GradeInfo grade;
	
	private Logger logger = Logger.getLogger(SubjectController.class);
	
	@RequestMapping("/listSubject")
	public String listSubject(Model model,Integer startPage,Integer handAdd,Integer examPaperId){		
		if (startPage==null) {
			startPage=1;
		}
		PageHelper.startPage(startPage, 10);
		List<SubjectInfo> list = sService.getListSubject();
		PageInfo<SubjectInfo> pageInfo = new PageInfo<SubjectInfo>(list);
		model.addAttribute("subjects", pageInfo);
		model.addAttribute("pageTotal", pageInfo.getPages());
        model.addAttribute("pageNow", startPage);
        
      		if (handAdd != null && handAdd == 1) {
      			model.addAttribute("handAdd", "1");
      		}
      		//如果是手动添加试题到试卷，则需要返回试卷编号, 且返回当前已经选择试题数量
 	           if (examPaperId != null) {
      			model.addAttribute("examPaperId", "1");
      			List<String> ids = (List<String>) model.addAttribute("ids");
      			if (ids == null) {
      				model.addAttribute("choosed", 0);
      			} else {				
      				model.addAttribute("choosed", ids.size());
      			}
      		}
		return "admin/subjects";
	}
	
	@RequestMapping("/toSubjectTest")
	public  String  toSubjectTest(Integer subjectId,Model model){
		System.out.println(subjectId);
		SubjectInfo sInfo = sService.getSubject(subjectId);
		if (sInfo!=null) {
			model.addAttribute("subject", sInfo);
			return "admin/subject-test";
		}
		return "error";
	}
	
	@RequestMapping(value="/updateSubject.json",produces={"application/json;charset=utf-8"},method=RequestMethod.POST)
	@ResponseBody
	public String  updateSubject(SubjectInfo sInfo){
		boolean flag = sService.updateSubject(sInfo);
		System.out.println(flag);
		
		return JSON.toJSONString(flag);
	}
	
	@RequestMapping(value="/delSubject.json",produces={"application/json;charset=utf-8"},method=RequestMethod.POST)
	@ResponseBody
	public String delSubject(Integer subjectId) {
		boolean flag = sService.delSubject(subjectId);
		JSONObject jObject = new JSONObject();
		if (flag) {
			System.out.println(flag);
			jObject.put("subjectId", "t");			
		}else {
			return "error";
		}
		return jObject.toJSONString();
	}
	
	@RequestMapping("/preAddSubject")
	public String  preAddSubject(Model model){
		List<CourseInfo> courses = cService.getCourseList();
		List<GradeInfo> grades = gService.getGradeList();
		model.addAttribute("grades", grades);
	    model.addAttribute("courses", courses);  
		return "admin/subject-test";
	}
	
	@RequestMapping(value = "/addSubject.json",produces = {"application/json;charset=utf-8"},method = RequestMethod.POST)
	@ResponseBody
	public String addSubject(SubjectInfo sInfo) {
		sInfo.setGradeId(2);
		boolean flag = sService.addSubject(sInfo);
	
			return JSON.toJSONString(flag);
	}
	
	@RequestMapping("/initImport")
	public String toImportSub(Model model){
		List<CourseInfo> courses = cService.getCourseList();
		List<GradeInfo> grades = gService.getGradeList();
		List<ExamPaperInfo> examPapers = epService.getExamPapersList();
		model.addAttribute("grades", grades);
	    model.addAttribute("courses", courses);
	    model.addAttribute("examPapers", examPapers);
		return "admin/importSubject";
	}
	
	@RequestMapping("/dispatcherUpload")
	public String dispatcherUpload(HttpServletRequest req,Model model,
			@RequestParam(value="division",required=false) Integer division,
			@RequestParam(value="courseId",required=false) Integer courseId,
			@RequestParam(value="gradeId",required=false) Integer gradeId,
			@RequestParam(value="examPaperId",required=false) Integer examPaperId,
			@RequestParam(value="importOption",required=false) String importOption,
			@RequestParam(value="examPaperEasy",required=false) Integer examPaperEasy,
			@RequestParam(value="examPaperName",required=false) String examPaperName,
			@RequestParam(value="examPaperTime",required=false) Integer examPaperTime,
			@RequestParam(value="inputfile",required=false) MultipartFile excel){
		   String savePath = "";
		   /** 保存上传 excel 文件 */
		   savePath = saveUploadFile(excel,req.getRealPath("/upload"));
		   File file = new File(savePath);
		   if (!file.exists()){
		   	file.mkdirs();
		   }
			System.out.println(file.getAbsolutePath());
			/** 解析上传 excel 文件, 得到试题集合 */
		   List<SubjectInfo> subjects = SubjectImportUtil.parseSubjectExcel(savePath, courseId, gradeId, division);
		   /** 只添加试题 */
		   if ("0".equals(importOption)) {
			Map<String, Object> subjectsMap = new HashMap<String, Object>();
			subjectsMap.put("subjects", subjects);
			importSubjectOnly(subjects,subjectsMap);
		}else if ("1".equals(importOption)) {	/** 添加试题到指定的已有试卷 */
			dispatcherExamPaperAndSubject(subjects,examPaperId);
		}else if ("2".equals(importOption)) {	/** 添加试题到指定的已有试卷 */
			/** 创建新试卷 */
			examPaper.setExamPaperName(examPaperName);
			examPaper.setExamPaperEasy(examPaperEasy);
			   examPaper.setExamPaperTime(examPaperTime);
			   examPaper.setGrade(grade);
			   examPaper.setDivision(division);
			   grade.setGradeId(gradeId);
			   int row = epService.isAddExamPaper(examPaper);
			   logger.info("添加的新试卷 编号 "+examPaper.getExamPaperId());
			dispatcherExamPaperAndSubject(subjects, examPaper.getExamPaperId());
		}
		   if (subjects.size()==0) {
			   model.addAttribute("fail", "操作处理失败，共添加<b style='color:red;'>"+subjects.size()+"</b>道题，请检查上传数据正确性！"); 
		}else{
			model.addAttribute("success", "操作处理成功，共添加"+subjects.size()+"道题");
		}
		
		return "reception/suc";
	}

	private void dispatcherExamPaperAndSubject(List<SubjectInfo> subjects, Integer examPaperId) {
	        List<Integer> subjectIds = new ArrayList<Integer>();
	        //试题总量统计
			int count = 0;
			//试题总分统计
			int score = 0;
			
			/** 添加试题 */
			for(SubjectInfo subjectInfo:subjects){
				int row = sService.isAddSubject(subjectInfo);
				score+=subjectInfo.getSubjectScore();
				subjectIds.add(subjectInfo.getSubjectId());
				count++;	
			}
			logger.info("添加试题 SIZE "+count);
			/** 添加试题到试卷 */
			Map<String, Object> esmMap = new HashMap<String,Object>();
			esmMap.put("examPaperId", examPaperId);
			esmMap.put("subjectId", subjectIds);
			esmService.isAddESM(esmMap);
			logger.info("添加试题 SIZE"+count+"SCORE"+score+"到试卷"+examPaperId);
			
			//修改试卷信息
			Map<String, Object>scoreWithNum = new HashMap<>();
			scoreWithNum.put("subjectNum", count);
			scoreWithNum.put("score", score);
			scoreWithNum.put("examPaperId", examPaperId);
			/** 修改试卷总分 */
			examPaperService.isUpdateExamPaperScore(scoreWithNum);
			/** 修改试卷试题总量 */
			examPaperService.isUpdateExamPaperSubjects(scoreWithNum);
	}

	private void importSubjectOnly(List<SubjectInfo> subjects, Map<String, Object> subjectsMap) {
		if (subjects!=null&&subjects.size()>0) {
			//添加试题
			int row = sService.isAddSubjects(subjectsMap);
			logger.info("只将 excel 中的试题添加到数据库成功 SIZE "+subjects.size());
		}else{
			logger.info("上传试题文件中不存在试题，或解析失败");
		}
	}

	private String saveUploadFile(MultipartFile excel, String realPath) {
		String fileName = excel.getOriginalFilename();
		System.out.println(fileName);
		logger.info("保存上传文件"+fileName+"到"+realPath);
		try {
			excel.transferTo(new File(realPath+"/"+fileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return realPath+"/"+fileName;
	}

	public SubjectService getsService() {
		return sService;
	}

	public void setsService(SubjectService sService) {
		this.sService = sService;
	}

	public CourseService getcService() {
		return cService;
	}

	public void setcService(CourseService cService) {
		this.cService = cService;
	}

	public GradeService getgService() {
		return gService;
	}

	public void setgService(GradeService gService) {
		this.gService = gService;
	}

	public ExamPaperService getEpService() {
		return epService;
	}

	public void setEpService(ExamPaperService epService) {
		this.epService = epService;
	}

	public ESMService getEsmService() {
		return esmService;
	}

	public void setEsmService(ESMService esmService) {
		this.esmService = esmService;
	}

	public ExamPaperService getExamPaperService() {
		return examPaperService;
	}

	public void setExamPaperService(ExamPaperService examPaperService) {
		this.examPaperService = examPaperService;
	}

	public ExamPaperInfo getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(ExamPaperInfo examPaper) {
		this.examPaper = examPaper;
	}

	public GradeInfo getGrade() {
		return grade;
	}

	public void setGrade(GradeInfo grade) {
		this.grade = grade;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}
