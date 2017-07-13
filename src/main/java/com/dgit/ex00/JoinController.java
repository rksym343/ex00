package com.dgit.ex00;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JoinController {

	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String getJoin() {
		logger.info("join Get 실행");
		return "joinForm";
	}

	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String postJoin(@ModelAttribute("name") String name, @ModelAttribute("pw") String pw) {
		logger.info("join Post 실행");
		return "JoinResult";
	}

}
