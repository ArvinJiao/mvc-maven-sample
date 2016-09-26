package com.xedaojia.common.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public @ResponseBody String exceptionHandlers(Exception e){
		return "{\"resultcode\":\"500\", \"resultdesc\":\"SYSTEM EXCEPTION\"}";
	}
}
