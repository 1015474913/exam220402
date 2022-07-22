package cn.kgc.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.GradeMapper;
import cn.kgc.exam.pojo.GradeInfo;

@Service
public class GradeServiceImpl implements GradeService{
	
	@Autowired
	private GradeMapper gMapper;
	
	@Override
	public List<GradeInfo> getGradeList() {
		
		return gMapper.getGradeList();
	}

}
