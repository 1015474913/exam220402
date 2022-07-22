package cn.kgc.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.ExamSubjectMiddleInfo;

public interface ExamSubjectMiddleInfoMapper {

	List<ExamSubjectMiddleInfo> viewSubjects(Integer examPaperId);

	int removeSubject(@Param("subjectId") Integer subjectId, @Param("examPaperId") Integer examPaperId);// 删除试卷中的题目

	ExamSubjectMiddleInfo getChooseSubId(@Param("subjectId") Integer subjectId,
			@Param("examPaperId") Integer examPaperId);// 查找试卷中是否有这个试题

	int addExamSubjectMiddleInfo(@Param("subjectId") Integer subjectId, @Param("examPaperId") Integer examPaperId);

	List<Integer> listExamPaper(Integer examPaperId);// 查询试卷中是所有试题Id

}
