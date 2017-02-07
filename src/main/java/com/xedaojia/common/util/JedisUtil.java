package com.xedaojia.common.util;

import java.util.List;
import java.util.Map;
import com.xedaojia.common.config.Configurer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis util
 * 
 * @author arvin.jiao
 *
 */
public class JedisUtil {

	private JedisPool jedisPool = null;
	private static final JedisUtil INSTANCE = new JedisUtil();

	private JedisUtil() {
		init();
	}

	public static JedisUtil getInstance() {
		return INSTANCE;
	}

	private void init() {
		String host = Configurer.getValue("redis.host");
		int port = Configurer.getInteger("redis.port");
		String password = Configurer.getValue("redis.password");
		int timeout = Configurer.getInteger("redis.timeout");
		int maxTotal = Configurer.getInteger("redis.jedisPoolConfig.maxTotal");
		int maxIdle = Configurer.getInteger("redis.jedisPoolConfig.maxIdle");
		int minIdle = Configurer.getInteger("redis.jedisPoolConfig.minIdle");
		long maxWaitMillis = Configurer.getLong("redis.jedisPoolConfig.maxWaitMillis");
		boolean testOnBorrow = Boolean.valueOf(Configurer.getValue("redis.jedisPoolConfig.testOnBorrow"));

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setTestOnBorrow(testOnBorrow);

		jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
	}

	public String set(final String key, final String value) {
		return execute(key, new JedisExecutor<String>() {
			@Override
			public String execute(Jedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	public String get(final String key) {
		return execute(key, new JedisExecutor<String>() {
			@Override
			public String execute(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	public Boolean exists(final String key) {
		return execute(key, new JedisExecutor<Boolean>() {
			@Override
			public Boolean execute(Jedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	public Long setnx(final String key, final String value) {
		return execute(key, new JedisExecutor<Long>() {
			@Override
			public Long execute(Jedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	public String setex(final String key, final int seconds, final String value) {
		return execute(key, new JedisExecutor<String>() {
			@Override
			public String execute(Jedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		});
	}

	public Long expire(final String key, final int seconds) {
		return execute(key, new JedisExecutor<Long>() {
			@Override
			public Long execute(Jedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}

	public Long incr(final String key) {
		return execute(key, new JedisExecutor<Long>() {
			@Override
			public Long execute(Jedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	public Long decr(final String key) {
		return execute(key, new JedisExecutor<Long>() {
			@Override
			public Long execute(Jedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	public Long hset(final String key, final String field, final String value) {
		return execute(key, new JedisExecutor<Long>() {
			@Override
			public Long execute(Jedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	public String hget(final String key, final String field) {
		return execute(key, new JedisExecutor<String>() {
			@Override
			public String execute(Jedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	public String hmset(final String key, final Map<String, String> hash) {
		return execute(key, new JedisExecutor<String>() {
			@Override
			public String execute(Jedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	public List<String> hmget(final String key, final String... fields) {
		return execute(key, new JedisExecutor<List<String>>() {
			@Override
			public List<String> execute(Jedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	public Long del(final String key) {
		return execute(key, new JedisExecutor<Long>() {
			@Override
			public Long execute(Jedis jedis) {
				return jedis.del(key);
			}
		});
	}

	public Map<String, String> hgetAll(final String key) {
		return execute(key, new JedisExecutor<Map<String, String>>() {
			@Override
			public Map<String, String> execute(Jedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	public void destroy() {
		this.jedisPool.close();
	}

	public <T> T execute(String key, JedisExecutor<T> executor) {
		Jedis jedis = jedisPool.getResource();
		T result = null;
		try {
			result = executor.execute(jedis);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}

	private interface JedisExecutor<T> {
		T execute(Jedis jedis);
	}

}
