package com.xedaojia.test.curator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorTest {
	
	private static final String connectString = "192.168.63.152:6688";
	private static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
	static Logger log = LoggerFactory.getLogger(CuratorTest.class);
	
	public static void main(String[] args) throws Exception {
		curd();
	}
	
	public static CuratorFramework newClient(){
		CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
		client.start();
		return client;
	} 
	
	public static void connect() throws Exception{
		CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
		client.start();
		String forPath = client.create().forPath("/xe", new byte[0]);
		System.out.println("forPath:" + forPath);
	}
	
	
	public static void lock(){
		InterProcessMutex lock = new InterProcessMutex(newClient(), "/lock");
		try {
			System.out.println("test1 start");
			if(lock.acquire(100, TimeUnit.SECONDS)){
				try{
					System.out.println("test1 processing");
					Thread.sleep(20000l);
				}finally{
					lock.release();
				}
			}
			System.out.println("test1 end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void leaderElection(){
		LeaderSelectorListener listener = new LeaderSelectorListener() {
			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState) {
				log.info(Thread.currentThread().getName() + " newState:" + newState);
				
			}
			@Override
			public void takeLeadership(CuratorFramework client) throws Exception {
				log.info(Thread.currentThread().getName() + " take leadership");
				Thread.sleep(2000l);
				log.info(Thread.currentThread().getName() + " release leadership");
			}
		};
		@SuppressWarnings("resource")
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				LeaderSelector selector = new LeaderSelector(newClient(), "/master", listener);
				selector.autoRequeue();
				selector.start();
				
			}
		};
		new Thread(runnable).start();
		new Thread(runnable).start();
		
		try {
			Thread.sleep(100000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void curd() throws Exception{
		CuratorFramework client = newClient();
		client.create().creatingParentsIfNeeded().forPath("/test/a", "1".getBytes());
		//client.setData().forPath("/test1","2".getBytes());
		//byte[] data = client.getData().forPath("/test");
		//log.info(new String(data));
		
		client.delete().deletingChildrenIfNeeded().forPath("/test");
		
		List<String> children = client.getChildren().forPath("/");
		log.info(children.toString());
	}
	
	
}
