package cn.kgc.exam.service;

import cn.kgc.exam.pojo.ExamHistoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamHistoryServiceImpl implements ExamHistoryService{
    @Autowired


    @Override
    public List<ExamHistoryInfo> getAllExamHistory(Integer studentId) {

        return null;
    }
}
