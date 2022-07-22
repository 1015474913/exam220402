package cn.kgc.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.CourseMapper;
import cn.kgc.exam.pojo.CourseInfo;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseMapper cMapper;
	
	@Override
	public List<CourseInfo> getCourseList() {
		
		return cMapper.getCourseList();
	}
	
	
}
