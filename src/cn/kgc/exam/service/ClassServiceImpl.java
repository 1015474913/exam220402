package cn.kgc.exam.service;

import cn.kgc.exam.mapper.ClassMapper;
import cn.kgc.exam.pojo.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<ClassInfo> getClassList() {

        return classMapper.showListClass();
    }
}
