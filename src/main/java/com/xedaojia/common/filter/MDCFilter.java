package com.xedaojia.common.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * MDC过滤器
 */
public class MDCFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(MDCFilter.class);
	private static final String SEQ = "seq";
	private static final Random random = new Random();
	
    public MDCFilter() {
    }

	public void destroy() {
		logger.info("MDCFilter destroy");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		MDC.put(SEQ, String.valueOf(random.nextLong()) + "|" + request.getRemoteAddr());
		chain.doFilter(request, response);
		MDC.remove(SEQ);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("MDCFilter init");
	}

}
