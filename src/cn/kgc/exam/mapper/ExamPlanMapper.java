package cn.kgc.exam.mapper;

import java.util.Date;
import java.util.List;

import cn.kgc.exam.pojo.ExamPlanInfo;
import org.apache.ibatis.annotations.Param;

public interface ExamPlanMapper {

	List<ExamPlanInfo> getExamPlanList();

    ExamPlanInfo getExamPlanById(@Param("examPlanId") Integer examPlanId);

    int addExamPlan(@Param("classId")Integer classId,@Param("courseId") Integer courseId, @Param("examPaperId") Integer examPaperId,@Param("beginTime") String beginTime);

    int deleteExamPlan(@Param("examPlanId") Integer examPlanId);

    ExamPlanInfo showExamPlanById(@Param("examPlanId") Integer examPlanId);
}
