package com.xedaojia.common.util;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * check sign util
 * @author Arvin Jiao
 *
 */
public class CheckSign {

	public static boolean check(Map<String,String> params, String sign, String key){
		if(params == null || params.isEmpty() || StringUtils.isBlank(sign) || StringUtils.isBlank(key))
			return false;
		String plaintext = getPlaintext(toTreeMap(params), key);
		String ciphertext = DigestUtils.md5Hex(plaintext);
		if(ciphertext.equals(sign))
			return true;
		return false;
	}
	
	private static String getPlaintext(TreeMap<String,String> sortParms, String key){
		
		StringBuilder sb = new StringBuilder();
		for (String s : sortParms.keySet()) {
			if(sb.length() > 0)
				sb.append("&");
			sb.append(s).append("=").append(sortParms.get(s));
		}
		sb.append("&").append("key=").append(key);
		
		return sb.toString();
	}
	
	private static TreeMap<String,String> toTreeMap(Map<String,String> params){
		TreeMap<String,String> tm = new TreeMap<String,String>();
		for (String key : params.keySet()) {
			tm.put(key, params.get(key));
		}
		return tm;
	}
}
