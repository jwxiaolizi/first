package cn.itcast.action.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.pojo.User;

public class MyIntercepter implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("------afterCompletion-----");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("--------postHandle-------");
		
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		if(request.getRequestURI().contains("user/login")){
			
			return true;
			
		}else{
			User user = (User) request.getSession().getAttribute("user");
			if(user!=null){
				return true;
			}else{
				request.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(request, response);
				return false;
			}
		}
	}
	
}
