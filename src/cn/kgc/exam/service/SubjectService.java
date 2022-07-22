package cn.kgc.exam.service;

import java.util.List;
import java.util.Map;

import cn.kgc.exam.pojo.SubjectInfo;

public interface SubjectService {

	List<SubjectInfo> getListSubject();

	SubjectInfo getSubject(Integer subjectId);

	boolean updateSubject(SubjectInfo subjectInfo);

	int getAllSubject();

	boolean delSubject(Integer subjectId);

	boolean addSubject(SubjectInfo sInfo);

   //批量添加试题
    int  isAddSubjects(Map<String, Object> subjectsMap);

	int isAddSubject(SubjectInfo subjectInfo);

}
