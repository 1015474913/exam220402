package cn.kgc.exam.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.ExamPaperMapper;
import cn.kgc.exam.pojo.ExamPaperInfo;

@Service
public class ExamPaperServiceImpl implements ExamPaperService{
	
	@Autowired
	private ExamPaperMapper epMapper;
	
	private Logger logger = Logger.getLogger(ExamPaperServiceImpl.class);
	
	@Override
	public int getAllExamPaper() {
		
		return epMapper.getAllExamPaper();
	}

	@Override
	public List<ExamPaperInfo> getExamPapersList() {
		
		return epMapper.getExamPapersList();
	}

	@Override
	public int isUpdateExamPaperScore(Map<String, Object> map) {
		logger.info("�޸��Ծ� "+map.get("examPaperId")+"���ܷ֣��䶯��ֵ��"+map.get("score"));
		return epMapper.isUpdateExamPaperScore(map);
	}

	@Override
	public int isUpdateExamPaperSubjects(Map<String, Object> map) {
		logger.info("�޸��Ծ�"+map.get("examPaperId")+"���ܷ֣��䶯��ֵ��"+map.get("score"));
		return epMapper.isUpdateExamPaperSubjects(map);
	}

	@Override
	public int isAddExamPaper(ExamPaperInfo examPaper) {

		return epMapper.isAddExamPaper(examPaper);
	}
	@Override
	public boolean deleteExamPaper(Integer examPaperId) {
		int n = epMapper.deleteExamPaper(examPaperId);
		if (n > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ExamPaperInfo getExamPaperById(Integer examPaperId) {

		return epMapper.getExamPaperById(examPaperId);
	}

	@Override
	public boolean updateExamPaper(ExamPaperInfo examPaperInfo) {
		int n = epMapper.updateExamPaper(examPaperInfo);
		if (n > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addExamPaper(ExamPaperInfo examPaperInfo) {
		int n = epMapper.addExamPaper(examPaperInfo);
		if (n > 0) {
			return true;
		}
		return false;
	}
}
