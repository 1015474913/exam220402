package cn.kgc.exam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.SubjectInfo;

public interface SubjectMapper {

	List<SubjectInfo> getListSubject();

	SubjectInfo getSubject(@Param("subjectId")Integer subjectId);

	int updateSubject(SubjectInfo subjectInfo);

	int getAllSubject();

	int delSubject(@Param("subjectId")Integer subjectId);

	int addSubject(SubjectInfo sInfo);

	int isAddSubjects(Map<String, Object> subjectsMap);

	int isAddSubject(SubjectInfo subjectInfo);

	List<Integer> listSubjectsId(@Param("courseId") Integer courseId, @Param("gradeId") Integer gradeId,
								 @Param("subjectEasy") Integer subjectEasy);// 查询满足条件的试题id集合
}
