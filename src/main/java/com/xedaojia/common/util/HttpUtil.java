package com.xedaojia.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http reqeust util
 * @author Arvin Jiao
 *
 */
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/*
	 * defines the overal connection limit for a conneciton pool
	 */
	private static final int maxTotal = 200;
	/*
	 * defines a connection limit per one HTTP route. In simple cases you can 
	 * understand this as a per target host limit. Under the hood things are 
	 * a bit more interesting: HttpClient maintains a couple of HttpRoute objects, 
	 * which represent a chain of hosts each, like proxy1 -> proxy2 -> targetHost. 
	 * Connections are pooled on per-route basis. In simple cases, when you're using 
	 * default route-building mechanism and provide no proxy suport, your routes are 
	 * likely to include target host only, so per-route connection pool limit effectively 
	 * becomes per-host limit.
	 */
	private static final int maxPerRoute = 20;
	private static final int connectTimeout = 5000;
	private static final int socketTimeout = 5000;

	/**
	 * create PoolingHttpClientConnectionManager by static inner class singleton
	 */
	private static class Singleton {
		public static final PoolingHttpClientConnectionManager cm;

		static {
			cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(maxTotal);
			cm.setDefaultMaxPerRoute(maxPerRoute);
		}
	}

	/**
	 * PoolingHttpClientConnectionManager maintains a maximum limit of
	 * connections on a per route basis and in total. Per default this
	 * implementation will create no more than {maxPerRoute} concurrent connections per
	 * given route and no more {maxTotal} connections in total. For many real-world
	 * applications these limits may prove too constraining, especially if they
	 * use HTTP as a transport protocol for their services.
	 * 
	 * @return
	 */
	private static PoolingHttpClientConnectionManager getConnectionManager() {
		return Singleton.cm;
	}

	/**
	 * create http client
	 * 
	 * @return
	 */
	private static HttpClient createHttpClient() {
		PoolingHttpClientConnectionManager cm = getConnectionManager();
		RequestConfig.Builder requestBuilder = RequestConfig.custom().setConnectTimeout(connectTimeout)
				.setSocketTimeout(socketTimeout);
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultRequestConfig(requestBuilder.build());
		builder.setConnectionManager(cm);
		return builder.build();
	}

	/**
	 * http get request
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
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
		} finally {
			try {
				if (rd != null)
					rd.close();
			} catch (Exception e) {
				logger.info("io close exception", e);
			}
		}
		return result.toString();
	}

	/**
	 * http post request
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, Map<String, String> params) {
		StringBuilder result = null;
		BufferedReader rd = null;
		try {
			HttpClient client = createHttpClient();
			HttpPost post = new HttpPost(url);

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			for (String s : params.keySet()) {
				urlParameters.add(new BasicNameValuePair(s, params.get(s)));
			}
			post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
			HttpResponse response = client.execute(post);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			result = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			logger.info("http post exception", e);
			return null;
		} finally {
			try {
				if (rd != null)
					rd.close();
			} catch (Exception e) {
				logger.info("io close exception", e);
			}
		}
		return result.toString();
	}
	
	
	/**
	 * http post with string entity
	 * @param url
	 * @param content
	 * @param contentType eg. ContentType.APPLICATION_JSON
	 * @return
	 */
	public static String post(String url, String content, ContentType contentType) {
		StringBuilder result = null;
		BufferedReader rd = null;
		try {
			HttpClient client = createHttpClient();
			HttpPost post = new HttpPost(url);
			HttpEntity entity = new StringEntity(content, contentType);
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			result = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			logger.info("http post exception", e);
			return null;
		} finally {
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
