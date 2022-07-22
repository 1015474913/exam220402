package cn.kgc.exam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.ExamPaperInfo;

public interface ExamPaperMapper {

	int getAllExamPaper();

	List<ExamPaperInfo> getExamPapersList();

	int isUpdateExamPaperScore(Map<String, Object> map);

	int isUpdateExamPaperSubjects(Map<String, Object> map);

	int isAddExamPaper(ExamPaperInfo examPaper);
	
	List<ExamPaperInfo> getExamPaperList(@Param("examPaperId")Integer examPaperId);

	ExamPaperInfo getExamPaper(@Param("examPaperId")Integer examPaperId);

    int deleteExamPaper(@Param("examPaperId") Integer examPaperId);

	ExamPaperInfo getExamPaperById(@Param("examPaperId") Integer examPaperId);

	int updateExamPaper(ExamPaperInfo examPaperInfo);

	int addExamPaper(ExamPaperInfo examPaperInfo);

    int minuScore(@Param("score") Integer score, @Param("examPaperId") Integer examPaperId);

	int updateExamPaperSubs(@Param("subjectNum") Integer subjectNum, @Param("totalScore") Integer totalScore, @Param("examPaperId") Integer examPaperId);
}
