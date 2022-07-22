package cn.kgc.exam.service;

import java.util.List;
import java.util.Map;

import cn.kgc.exam.pojo.ExamPaperInfo;

public interface ExamPaperService {

	int getAllExamPaper();

	List<ExamPaperInfo> getExamPapersList();

	int isUpdateExamPaperScore(Map<String, Object> scoreWithNum);

	int isUpdateExamPaperSubjects(Map<String, Object> scoreWithNum);

	int isAddExamPaper(ExamPaperInfo examPaper);

	boolean deleteExamPaper(Integer examPaperId);

	ExamPaperInfo getExamPaperById(Integer examPaperId);

	boolean updateExamPaper(ExamPaperInfo examPaperInfo);

	boolean addExamPaper(ExamPaperInfo examPaperInfo);
}
