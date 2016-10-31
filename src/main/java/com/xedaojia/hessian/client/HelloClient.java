package com.xedaojia.hessian.client;

import java.net.MalformedURLException;


import com.caucho.hessian.client.HessianProxyFactory;
import com.xedaojia.hessian.HelloService;

public class HelloClient {

	private static String URL = "http://localhost/helloService.s";
	
	public static void main(String[] args) throws MalformedURLException {
		HessianProxyFactory factory = new HessianProxyFactory();
		HelloService helloService = (HelloService)factory.create(HelloService.class, URL);
		System.out.println(helloService.say("jack"));
		System.out.println(helloService.say("jack"));
		System.out.println(helloService.say("jack"));
		System.out.println(helloService.say("jack"));
		System.out.println(helloService.say("jack"));
	}
}
