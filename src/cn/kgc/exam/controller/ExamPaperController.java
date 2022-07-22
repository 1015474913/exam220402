package cn.kgc.exam.controller;

import java.util.List;

import cn.kgc.exam.service.GradeService;
import cn.kgc.exam.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.kgc.exam.pojo.ExamPaperInfo;
import cn.kgc.exam.pojo.ExamSubjectMiddleInfo;
import cn.kgc.exam.pojo.GradeInfo;
import cn.kgc.exam.pojo.SubjectInfo;
import cn.kgc.exam.service.ExamPaperService;
import cn.kgc.exam.service.ExamSubjectMiddleInfoService;

@Controller
@RequestMapping("/edu/admin")
public class ExamPaperController {
	@Autowired
	ExamPaperService examPaperService;
	@Autowired
	GradeService gradeService;
	@Autowired
	ExamSubjectMiddleInfoService examSubjectMiddleInfoService;
	@Autowired
	SubjectService subjectService;

	// �鿴�����Ծ�
	@RequestMapping("/listExamPaper")
	public String listExamPaper(Model model, Integer startPage) {
		if (startPage == null) {
			startPage = 1;
		}
		PageHelper.startPage(startPage, 8);
		List<ExamPaperInfo> list = examPaperService.getExamPapersList();
		model.addAttribute("examPapers", list);
		PageInfo<ExamPaperInfo> pageInfo = new PageInfo<ExamPaperInfo>(list);
		model.addAttribute("pageInfo", pageInfo);

		model.addAttribute("pageNow", startPage);// ��ǰҳ
		model.addAttribute("pageTotal", pageInfo.getPages());// ��ҳ��
		return "admin/examPapers";

	}

	// ɾ���Ծ�
	@RequestMapping("/deleteExamPaper")
	public String deleteExamPaper(Integer examPaperId) {
		boolean flag = examPaperService.deleteExamPaper(examPaperId);
		if (flag) {
			return "redirect:/edu/admin/getExamPapersList";
		}
		return "error";
	}

	// �޸��Ծ�
	@RequestMapping("/getExamPaperById")
	public String getExamPaperById(Integer examPaperId, Model model) {
		ExamPaperInfo exam = examPaperService.getExamPaperById(examPaperId);
		model.addAttribute("examPaper", exam);

		List<GradeInfo> list = gradeService.getGradeList();
		model.addAttribute("grades", list);
		return "admin/examPaperedit";
	}

	// �޸��Ծ�
	@RequestMapping("/updateExamPaper")
	public String updateExamPaper(ExamPaperInfo examPaperInfo) {
		boolean flag = examPaperService.updateExamPaper(examPaperInfo);
		if (flag) {
			return "redirect:/edu/admin/getExamPapersList";
		}
		return "error";

	}

	// ȥ����������
	@RequestMapping("/toAddExamPaper")
	public String toAddExamPaper(Model model) {
		List<GradeInfo> list = gradeService.getGradeList();
		model.addAttribute("grades", list);
		return "admin/examPapereAdd";
	}

	// ����Ծ�
	@RequestMapping("/addExamPaper")
	public String addExamPaper(ExamPaperInfo examPaperInfo) {
		boolean flag = examPaperService.addExamPaper(examPaperInfo);
		if (flag) {
			return "redirect:/edu/admin/listExamPaper";
		}
		return "error";
	}

	// �鿴�Ծ��е���������
	@RequestMapping(value = "/toViewSubjects", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String toViewSubjects(Integer examPaperId, Model model) {
		List<ExamSubjectMiddleInfo> list = examSubjectMiddleInfoService.viewSubjects(examPaperId);
		String json = JSONObject.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
		return json;
	}

	// �Ƴ�����
	@RequestMapping(value = "/removeSubjectFromPaper", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeSubjectFromPaper(Integer subjectId, Integer examPaperId, Integer score) {
		System.out.println(examPaperId + "," + subjectId);
		boolean flag = examSubjectMiddleInfoService.removeSubject(subjectId, examPaperId, score);
		JSONObject json = new JSONObject();
		if (flag) {
			json.put("result", "true");
		} else {
			json.put("result", "flase");
		}
		return json.toJSONString();

	}

	// �鿴��������
	@RequestMapping("/listSubject")
	public String listSubject(Model model, Integer startPage, Integer handAdd, Integer examPaperId) {
		if (startPage == null) {
			startPage = 1;
		}
		PageHelper.startPage(startPage, 6);
		List<SubjectInfo> list = subjectService.getListSubject();
		PageInfo<SubjectInfo> pageInfo = new PageInfo<SubjectInfo>(list);
		model.addAttribute("subjects", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("pageTotal", pageInfo.getPages());// ��ҳ��
		model.addAttribute("pageNow", startPage);// ��ǰҳ
		model.addAttribute("handAdd", handAdd);

		// ������ֶ�������⵽�Ծ�����Ҫ�����Ծ���, �ҷ��ص�ǰ�Ѿ�ѡ����������
//		if (examPaperId != null) {
		model.addAttribute("examPaperId", examPaperId);
//			List<String> ids = (List<String>) model.addAttribute("ids");
//			if (ids == null) {
//				model.addAttribute("choosed", 0);
//			} else {
//				model.addAttribute("choosed", ids.size());
//			}
//		}
		return "admin/subjects";
	}

	// ��ѯ�����Ƿ��ظ�
	@RequestMapping("/getChooseSubId")
	@ResponseBody
	public String getChooseSubId(Integer subjectId, Integer examPaperId) {
		ExamSubjectMiddleInfo examSubjectMiddleInfo = examSubjectMiddleInfoService.getChooseSubId(subjectId,
				examPaperId);
		if (examSubjectMiddleInfo != null) {
			return "f-exists-";
		} else {
			return "";
		}
	}

	// �ֶ��������
	@RequestMapping("/handAdd")
	@ResponseBody
	public String handAdd(Integer examPaperId, String subIds) {
		int n = examSubjectMiddleInfoService.addExamSubjectMiddleInfo(subIds, examPaperId);
		if (n > 0) {
			return "success";
		} else {
			return "error";
		}
	}

	// ȥ�Զ�����������
	@RequestMapping("/toAutoSubjectSelect")
	public String toAutoSubjectSelect(Integer examPaperId, Model model) {
		model.addAttribute("examPaperId", examPaperId);
		return "admin/autoSubjectSelect";
	}

	// �Զ��������
	@RequestMapping(value = "/autoAddSubject", method = RequestMethod.POST)
	@ResponseBody
	public String autoAddSubject(Integer examPaperId, Integer subjectSum, Integer courseId, Integer gradeId,
			Integer subjectEasy) {
		boolean flag = examSubjectMiddleInfoService.autoAddSubject(examPaperId, subjectSum, courseId, gradeId,
				subjectEasy);
		if (flag) {
			return "true";
		}
		return "false";
	}
}
