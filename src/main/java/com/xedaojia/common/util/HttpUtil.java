package com.xedaojia.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http reqeust util
 * @author Arvin Jiao
 *
 */
public class HttpUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	private static final int maxTotal = 200;
	private static final int maxPerRoute = 20;
	private static final int connectTimeout = 3000;
	private static final int socketTimeout = 3000;
	
	/**
	 * create PoolingHttpClientConnectionManager by static inner class singleton
	 */
	private static class Singleton{
		public static final PoolingHttpClientConnectionManager cm;
		static {
			 cm = new PoolingHttpClientConnectionManager();
			 cm.setMaxTotal(maxTotal);
			 cm.setDefaultMaxPerRoute(maxPerRoute);
		}
	}
	
	/**
	 * PoolingHttpClientConnectionManager maintains a maximum limit of connections
	 * on a per route basis and in total. Per default this implementation will
	 * create no more than 2 concurrent connections per given route and no more 20
	 * connections in total. For many real-world applications these limits may
	 * prove too constraining, especially if they use HTTP as a transport
	 * protocol for their services.
	 * @return
	 */
	private static PoolingHttpClientConnectionManager getConnectionManager() {
		return Singleton.cm;
	}

	/**
	 * create http client
	 * @return
	 */
	private static HttpClient createHttpClient() {
		PoolingHttpClientConnectionManager cm = getConnectionManager();
		RequestConfig.Builder requestBuilder = RequestConfig.custom()
				.setConnectTimeout(connectTimeout)
				.setSocketTimeout(socketTimeout);
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultRequestConfig(requestBuilder.build());
		builder.setConnectionManager(cm);
		return builder.build();
	}
	
	/**
	 * http get request
	 * @param url
	 * @return
	 */
	public static String get(String url){
		StringBuilder result = null;
		BufferedReader rd = null;
		try {
			HttpClient client = createHttpClient();
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			result = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			logger.info("http get exception", e);
			return null;
		}finally {
			try {
				if (rd != null)
					rd.close();
			} catch (Exception e) {
				logger.info("io close exception", e);
			}
		}
		return result.toString();
	}

	
}
