package com.xedaojia.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;

import com.xedaojia.common.util.CheckSign;
import com.xedaojia.common.util.HttpUtil;

public class SomeTest {

	
	public void test(){
		String data = "apple";
		String md5Hex = DigestUtils.md5Hex(data);
		System.out.println(md5Hex);
	}
	
	
	public void testSign(){
		Map<String,String> params = new HashMap<String,String>();
		
		String key = "xxxx";
		String data = "a=a1&c=c1&d=d1&key=" + key;
		String sign = DigestUtils.md5Hex(data);
		
		params.put("a", "a1");
		params.put("c", "c1");
		params.put("d", "d1");
		
		boolean check = CheckSign.check(params, sign, key);
		Assert.assertEquals(true, check);
	}
	
	
	public void testhttp() throws ClientProtocolException, IOException{
		String url = "http://localhost/crowdsourcing/save";
		String string = HttpUtil.get(url);
		System.out.println(string);	
		
		String s = HttpUtil.get(url);
		System.out.println(s);
		
	}
}
