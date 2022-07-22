package cn.kgc.exam.service;

import cn.kgc.exam.pojo.ExamHistoryInfo;

import java.util.List;

public interface ExamHistoryService {


    List<ExamHistoryInfo> getAllExamHistory(Integer studentId);
}
