package cn.kgc.exam.service;

import java.util.Date;
import java.util.List;

import cn.kgc.exam.pojo.ExamPlanInfo;
import org.springframework.web.bind.annotation.RequestParam;

public interface ExamPlanService {

	List<ExamPlanInfo> getExamPlanList();

    ExamPlanInfo getExamPlanById(Integer examPlanId);

    boolean addExamPlan(Integer classId,Integer courseId,Integer examPaperId,String beginTime);

    boolean deleteExamPlan(Integer examPlanId);

    ExamPlanInfo showExamPlanById(Integer examPlanId);
}
