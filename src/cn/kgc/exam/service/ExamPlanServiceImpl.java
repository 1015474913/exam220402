package cn.kgc.exam.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.ExamPlanMapper;
import cn.kgc.exam.pojo.ExamPlanInfo;
@Service
public class ExamPlanServiceImpl implements ExamPlanService{
	
	@Autowired
	private ExamPlanMapper examPlanMapper;
	
	@Override
	public List<ExamPlanInfo> getExamPlanList() {
		
		
		return examPlanMapper.getExamPlanList();
	}

	@Override
	public ExamPlanInfo getExamPlanById(Integer examPlanId) {

		return examPlanMapper.getExamPlanById(examPlanId);
	}

	@Override
	public boolean addExamPlan(Integer classId, Integer courseId, Integer examPaperId, String beginTime) {
		int num = examPlanMapper.addExamPlan(classId,courseId,examPaperId,beginTime);
		if (num>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteExamPlan(Integer examPlanId) {
		int num = examPlanMapper.deleteExamPlan(examPlanId);
		if (num>0){
			return true;
		}
		return false;
	}

	@Override
	public ExamPlanInfo showExamPlanById(Integer examPlanId) {

		return examPlanMapper.showExamPlanById(examPlanId);
	}
}
