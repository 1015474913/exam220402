package cn.kgc.exam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.kgc.exam.pojo.StudentInfo;
import cn.kgc.exam.pojo.TeacherInfo;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// 跳转前拦截
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// 跳转后拦截
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {           
		    StudentInfo studentInfo =(StudentInfo)request.getSession().getAttribute("sessionScope");
			if(studentInfo!=null) {
				return true;
			}
			request.setAttribute("error", "请先登录");
			request.getRequestDispatcher("/reception/toLogin");
			return false;
			
			
			
		  }
	
	
		
	}


