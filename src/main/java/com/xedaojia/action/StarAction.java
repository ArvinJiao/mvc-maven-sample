package com.xedaojia.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xedaojia.domain.Users;
import com.xedaojia.mapper.UsersMapper;
import com.xedaojia.service.UsersService;



@Controller
public class StarAction {
	
	Logger logger = LoggerFactory.getLogger(StarAction.class);
	
	@Autowired
	UsersService usersService;
	
	@RequestMapping("star/test")
	public @ResponseBody String test(){
		logger.error("it is a test");
		return "success";
	}
	
	@RequestMapping("users/get")
	public @ResponseBody Users get(Integer id){
		Users users = usersService.getById(id);
		return users;
	}
}
