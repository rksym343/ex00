package com.dgit.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInsertceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandler~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		System.out.println("BEAN : " + method.getBean()); // controller class를 알 수 있음
		System.out.println("METHOD : "+methodObj); // controller class안의 어떤 함수가 호출되는지를 알 수 있음
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("~~~~~~~~~~~~~~~postHandler~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Object result = modelAndView.getModel().get("result");
		
		if(result != null){
			request.getSession().setAttribute("test", "정신 차리자");
			response.sendRedirect("doA"); // 흐름 제어... 필요에 따라 다른 곳으로 보냄
		}
	}
	
	
}
