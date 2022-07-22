package cn.kgc.exam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.kgc.exam.pojo.TeacherInfo;

public class LoginInterceptor1 implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		TeacherInfo teacherInfo =(TeacherInfo)request.getSession().getAttribute("loginTeacher");
		if (teacherInfo!=null) {
			return true;
		}else {
			request.setAttribute("error", "请先登录");
			request.getRequestDispatcher("/reception/toLogin");
			return false;
		}
		
		
	}

}
