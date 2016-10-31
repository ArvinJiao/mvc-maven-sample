package com.xedaojia.hessian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceiImpl implements HelloService{

	Logger logger = LoggerFactory.getLogger(HelloServiceiImpl.class);
	
	@Override
	public String say(String name) {
		logger.info("name:" + name);
		return "hello " + name;
	}

}
