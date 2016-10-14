package com.xedaojia.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class StarAction {
	
	Logger logger = LoggerFactory.getLogger(StarAction.class);

	@RequestMapping("star/test")
	public @ResponseBody String test(){
		logger.error("it is a test");
		return "success";
	}
}
