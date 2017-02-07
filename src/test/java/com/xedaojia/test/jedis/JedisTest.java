package com.xedaojia.test.jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xedaojia.common.util.JedisUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class JedisTest {

	Logger log = LoggerFactory.getLogger(JedisTest.class);
	
	@Test
	public void test(){
		JedisUtil jedisUtil = JedisUtil.getInstance();
		String key = "dell";
		Map<String, String> hash = new HashMap<>();
		hash.put("name", "jack");
		hash.put("age", "10");
		jedisUtil.hmset(key, hash);
		List<String> result = jedisUtil.hmget(key, "name", "age");
		log.info("result:" + result);
		Long del = jedisUtil.del(key);
		log.info("del:" + del);
	}
}
