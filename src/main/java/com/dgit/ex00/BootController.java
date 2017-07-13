package com.dgit.ex00;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BootController {
	
	@RequestMapping("grid01")
	public String goGrid() {
		return "grid_01";
	}
	
	@RequestMapping("grid02")
	public String goGrid02() {
		return "grid_02";
	}
}
