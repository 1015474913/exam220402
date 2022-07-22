package cn.kgc.exam.pojo;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
  *
  * <p>Title: GradeInfo</p>
  * <p>Description: 年级实体类</p>
  * @author: taohan
  * @date: 2018-8-14
  * @time: 上午8:57:29
  * @version: 1.0
  */

@Component
@Scope("prototype")
public class GradeInfo implements Serializable{

	private Integer gradeId;
	private String gradeName;
	private ClassInfo classInfo;
	
	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public GradeInfo(Integer gradeId) {
		super();
		this.gradeId = gradeId;
	}
	
	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public GradeInfo() {
		super();
	}

	@Override
	public String toString() {
		return "GradeInfo [gradeId=" + gradeId + ", gradeName=" + gradeName + ", classInfo=" + classInfo + "]";
	}



}
