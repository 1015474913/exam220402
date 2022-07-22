package cn.kgc.exam.service;

import java.util.List;

import cn.kgc.exam.pojo.ExamSubjectMiddleInfo;

public interface ExamSubjectMiddleInfoService {

	List<ExamSubjectMiddleInfo> viewSubjects(Integer examPaperId);

	boolean removeSubject(Integer subjectId, Integer examPaperId, Integer score);

	ExamSubjectMiddleInfo getChooseSubId(Integer subjectId, Integer examPaperId);

	int addExamSubjectMiddleInfo(String subjectIds, Integer examPaperId);

	boolean autoAddSubject(Integer examPaperId, Integer subjectSum, Integer courseId, Integer gradeId,
			Integer subjectEasy);

}
