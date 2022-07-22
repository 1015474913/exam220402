package cn.kgc.exam.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.SubjectMapper;
import cn.kgc.exam.pojo.SubjectInfo;

@Service
public class SubjectServiceImpl implements SubjectService{
	
	@Autowired
	private SubjectMapper sMapper;
	
	@Override
	public List<SubjectInfo> getListSubject() {
		
		return sMapper.getListSubject();
	}

	@Override
	public SubjectInfo getSubject(Integer subjectId) {
		
		return sMapper.getSubject(subjectId);
		
	}

	@Override
	public boolean updateSubject(SubjectInfo subjectInfo) {
	  int num = sMapper.updateSubject(subjectInfo);
		if (num>0) {
			return true;
		}
		
		return false;
	}

	@Override
	public int getAllSubject() {
		
		
		return sMapper.getAllSubject();
	}

	@Override
	public boolean delSubject(Integer subjectId) {
		int num = sMapper.delSubject(subjectId);
		if (num>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addSubject(SubjectInfo sInfo) {
		int num = sMapper.addSubject(sInfo);
		if (num>0) {
			return true;
		}
		
		return false;
	}

	@Override
	public int isAddSubjects(Map<String, Object> subjectsMap) {
		
		return sMapper.isAddSubjects(subjectsMap);
	}

	@Override
	public int isAddSubject(SubjectInfo subjectInfo) {
	   
		return sMapper.isAddSubject(subjectInfo);
	}

}
