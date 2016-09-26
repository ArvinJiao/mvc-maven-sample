package com.xedaojia.test.guava;

import org.junit.Test;

import com.google.common.base.Optional;

public class OptionalTest {
	
	@Test
	public void test(){
		String s = null;
		Optional<String> o = Optional.of(s);
	}
}
