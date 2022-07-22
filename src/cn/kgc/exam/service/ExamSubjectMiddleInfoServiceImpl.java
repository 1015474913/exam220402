package cn.kgc.exam.service;

import java.util.List;

import cn.kgc.exam.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.kgc.exam.mapper.ExamPaperMapper;
import cn.kgc.exam.mapper.ExamSubjectMiddleInfoMapper;
import cn.kgc.exam.pojo.ExamSubjectMiddleInfo;
import cn.kgc.exam.pojo.SubjectInfo;

@Service
public class ExamSubjectMiddleInfoServiceImpl implements ExamSubjectMiddleInfoService {
	@Autowired
	ExamSubjectMiddleInfoMapper examSubjectMiddleInfoMapper;
	@Autowired
	ExamPaperMapper examPaperMapper;
	@Autowired
	SubjectMapper subjectMapper;

	@Override
	public List<ExamSubjectMiddleInfo> viewSubjects(Integer examPaperId) {
		return examSubjectMiddleInfoMapper.viewSubjects(examPaperId);
	}

	// �Ƴ�����
	@Override
	public boolean removeSubject(Integer subjectId, Integer examPaperId, Integer score) {
		int n = examSubjectMiddleInfoMapper.removeSubject(subjectId, examPaperId);// ɾ���м�������Ϣ
		int m = examPaperMapper.minuScore(score, examPaperId);// �޸��Ծ����������ͷ���
		if (n > 0 & m > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ExamSubjectMiddleInfo getChooseSubId(Integer subjectId, Integer examPaperId) {// �ж��Ƿ����ظ�������
		return examSubjectMiddleInfoMapper.getChooseSubId(subjectId, examPaperId);
	}

	@Override
	@Transactional
	public int addExamSubjectMiddleInfo(String subjectIds, Integer examPaperId) {
		Integer row = 0;

		String[] subIds = subjectIds.split(",");

		Integer totalScore = 0;
		for (String subId : subIds) {
			int sid = Integer.parseInt(subId);
			row += examSubjectMiddleInfoMapper.addExamSubjectMiddleInfo(sid, examPaperId);
			// ��ȡ����
			SubjectInfo sub = subjectMapper.getSubject(sid);
			totalScore += sub.getSubjectScore();
		}
		// �޸��Ծ�����������ͷ���
		examPaperMapper.updateExamPaperSubs(subIds.length, totalScore, examPaperId);
		return row;
	}

	@Override
	public boolean autoAddSubject(Integer examPaperId, Integer subjectSum, Integer courseId, Integer gradeId,
			Integer subjectEasy) {
		List<Integer> list1 = examSubjectMiddleInfoMapper.listExamPaper(examPaperId);// ��ѯ�Ծ��е�id����
		List<Integer> list2 = subjectMapper.listSubjectsId(courseId, gradeId, subjectEasy);// ��ѯ������з���������id����
		for (Integer integer : list2) {
			if (list2.contains(integer)) {
				list1.remove(integer);
			}
		}

		if (list2.size() < subjectSum) {
			return false;
		}
		Integer countScore = 0;// �ܷ���
		int n = 0;// ��ӵ��м���Ӱ������
		for (Integer arr : list2) {
			for (int i = 0; i < subjectSum; i++) {
				SubjectInfo subjectInfo = subjectMapper.getSubject(arr);
				n = examSubjectMiddleInfoMapper.addExamSubjectMiddleInfo(arr, examPaperId);// ������ӵ��м��
				countScore += subjectInfo.getSubjectScore();// �ܷ���
			}
			break;
		}
		int m = examPaperMapper.updateExamPaperSubs(subjectSum, countScore, examPaperId);// �޸��Ծ����ķ�����������
		if (m > 0 & n > 0) {
			return true;
		}
		return false;
	}

}
