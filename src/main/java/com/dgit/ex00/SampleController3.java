package com.dgit.ex00;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController3 {

	@RequestMapping("doG")
	public String doG(RedirectAttributes attr) {
		//model.addAttribute("msg", "Command가 doH를 칠 경우 포워드로 result가 실행됨");
		attr.addFlashAttribute("msg", "Command가 doG를 칠 경우 리다이렉트로 result가 실행됨");
		return "redirect:/result";
	}

	@RequestMapping("result")
	public String doResult() {
		return "result";
	}
	
	@RequestMapping("doH")
	public String doH(Model model) {
		model.addAttribute("msg", "Command가 doH를 칠 경우 포워드로 result가 실행됨");
		return "forward:/result";
	}
}
