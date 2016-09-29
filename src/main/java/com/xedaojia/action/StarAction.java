package com.xedaojia.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StarAction {

	@RequestMapping("star/test")
	public @ResponseBody String test(){
		return "success";
	}
}
