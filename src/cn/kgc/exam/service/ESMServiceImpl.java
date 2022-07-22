package cn.kgc.exam.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.ESMMapper;

@Service
public class ESMServiceImpl implements ESMService{
	
	@Autowired
	private ESMMapper esmMapper;
	
	@Override
	public int isAddESM(Map<String, Object> esmMap) {
	
		return esmMapper.isAddESM(esmMap);
	}

}
