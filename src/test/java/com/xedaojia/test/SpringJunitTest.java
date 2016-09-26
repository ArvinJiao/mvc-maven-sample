package com.xedaojia.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xedaojia.common.config.Configurer;
import com.xedaojia.crowdsource.domain.MainOrderInfo;
import com.xedaojia.crowdsource.mapper.MainOrderInfoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class SpringJunitTest extends AbstractJUnit4SpringContextTests{

	Logger log = LoggerFactory.getLogger(SpringJunitTest.class);
	
	@Autowired
	private MainOrderInfoMapper mainOrderInfoMapper;
	
	@Test
	public void test(){
		String username = Configurer.getValue("username");
		log.info("-------------username:" + username);
	}
	
	
	
	
/*	
 * 分页
 * public Page<MainOrderInfo> selectZoneList() {
		PageHelper.startPage(2, 5).setOrderBy("id");
		Page<MainOrderInfo> page = (Page<MainOrderInfo>)mainOrderInfoMapper.selectList();
		logger.info("-------------------");
		logger.info(page.get(0).toString());
		logger.info(page.toString());
		return null;
	}*/
	
}
