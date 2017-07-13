package com.dgit.ex00;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("doA")
	public void doA() {
		System.out.println("doA");
		logger.info("doA를 실행하였습니다.");
		
		// return이 없으면 위 커맨드(doA)와 동일한 파일명의 jsp 파일을 찾아서 연결함
	}
	
	@RequestMapping("doB")
	public String doB(Model model) {
		System.out.println("doB");
		model.addAttribute("result", "doB go");
		return "home";
	}
	
	@RequestMapping("doC")
	public String doC(String msg, Model model) {
		System.out.println(msg);
		model.addAttribute("msg", msg);
		return "result";
	}
	
	@RequestMapping("doD")
	public String doD(@ModelAttribute("msg") String msg) {
		//msg라는 이름의 파라미터값을 view까지 자동 전달
		return "result";
	}
	
	@RequestMapping("doD2")
	public String doD2(@ModelAttribute("test") int msg, @ModelAttribute("aa") String aa) {
		//msg라는 이름의 파라미터값을 view까지 자동 전달
		return "result";
	}
}
