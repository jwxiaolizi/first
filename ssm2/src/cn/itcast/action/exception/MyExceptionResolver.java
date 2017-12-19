package cn.itcast.action.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2,
			Exception exception) {
		ModelAndView model = new ModelAndView();
		if(exception instanceof MyException){
			
			model.addObject("message", "您购买的商品已经下架...");
		}else{
			
			model.addObject("message", "系统繁忙中.请稍后再试!!!");
		}
		model.setViewName("error");
		return model;
	}
	
}
