package com.dgit.ex00;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CalController {

	private static final Logger logger = LoggerFactory.getLogger(CalController.class);
	
	@RequestMapping(value = "cal", method = RequestMethod.GET)
	public String getCal() {
		logger.info("get");
		return "calculator";
	}
	
	@RequestMapping(value = "cal", method = RequestMethod.POST)
	public String postCal(@ModelAttribute("num1") int num1, @ModelAttribute("num2") int num2, Model model) {
		model.addAttribute("result", num1 + num2);
		return "calResult";
	}
}
