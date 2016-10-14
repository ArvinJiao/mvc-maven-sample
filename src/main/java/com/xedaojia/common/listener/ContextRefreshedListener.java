package com.xedaojia.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{

	private static final Logger logger = LoggerFactory.getLogger(ContextRefreshedListener.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("\n                   _ooOoo_"
				+ "\n                  o8888888o"
				+ "\n                  88\" . \"88"
				+ "\n                  (| -_- |)"
				+ "\n                  O\\  =  /O"
				+ "\n               ____/`---'\\____"
				+ "\n             .'  \\\\|     |//  `."
				+ "\n            /  \\\\|||  :  |||//  \\"
				+ "\n           /  _||||| -:- |||||-  \\"
				+ "\n           |   | \\\\\\  -  /// |   |"
				+ "\n           | \\_|  ''\\---/''  |   |"
				+ "\n           \\  .-\\__  `-`  ___/-. /"
				+ "\n         ___`. .'  /--.--\\  `. . __"
				+ "\n      .\"\" '<  `.___\\_<|>_/___.'  >'\"\"."
				+ "\n     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |"
				+ "\n     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /"
				+ "\n======`-.____`-.___\\_____/___.-`____.-'======"
				+ "\n                   `=---='"
				+ "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
				+ "\n           佛祖保佑       永无BUG");
	}

}
