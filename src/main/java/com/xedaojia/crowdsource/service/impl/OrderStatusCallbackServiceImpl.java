package com.xedaojia.crowdsource.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.xedaojia.crowdsource.domain.MainOrderInfo;
import com.xedaojia.crowdsource.domain.MainOrderInfoExtend;
import com.xedaojia.crowdsource.domain.OrderStatusCallback;
import com.xedaojia.crowdsource.domain.OrderUpdateTask;
import com.xedaojia.crowdsource.domain.Ordertracks;
import com.xedaojia.crowdsource.mapper.OrderStatusCallbackMapper;
import com.xedaojia.crowdsource.service.MainOrderInfoExtendService;
import com.xedaojia.crowdsource.service.MainOrderInfoService;
import com.xedaojia.crowdsource.service.OrderStatusCallbackService;
import com.xedaojia.crowdsource.service.OrderUpdateTaskService;
import com.xedaojia.crowdsource.service.OrdertracksService;

/**
 * 订单状态回调
 * @author jiaoyx01
 *
 */
@Service
@Transactional
public class OrderStatusCallbackServiceImpl implements OrderStatusCallbackService {

	private static final Logger logger = LoggerFactory.getLogger(OrderStatusCallbackServiceImpl.class);

	@Autowired
	private MainOrderInfoService mainOrderInfoService;
	@Autowired
	private MainOrderInfoExtendService mainOrderInfoExtendService;
	@Autowired
	private OrdertracksService ordertracksService;
	@Autowired
	private OrderUpdateTaskService orderUpdateTaskService;
	@Autowired
	private OrderStatusCallbackMapper orderStatusCallbackMapper;

	/**
	 * 处理回调
	 */
	@Override
	public Map<String, Object> process(OrderStatusCallback orderStatusCallback) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String orderId = orderStatusCallback.getOrderId();
		Integer orderStatusCb = orderStatusCallback.getOrderStatus();
		logger.info("订单id:"+orderId);
		MainOrderInfo mainOrderInfo = mainOrderInfoService.getByOrderId(orderId);
		MainOrderInfoExtend mainOrderInfoExtend = mainOrderInfoExtendService.getByOrderId(orderId);
		//判断订单是否存在
		if(mainOrderInfo == null || mainOrderInfoExtend == null){
			logger.error("订单号("+orderId+")不存在");
			resultMap.put("resultcode", "1");
			resultMap.put("resultdesc", "订单号("+orderId+")不存在");
			return resultMap;
		}
		
		//保存回调信息
		int result = this.save(orderStatusCallback);
		logger.info("保存回调信息成功 result:"+result);
		
		//验证平台id
		Integer thirdPartyType = getThirdPartyType(orderStatusCallback.getPlatform());
		if(mainOrderInfoExtend.getThirdPartyType() != null && mainOrderInfoExtend.getThirdPartyType() != thirdPartyType){
			resultMap.put("resultcode", "1");
			resultMap.put("resultdesc", "平台id("+orderStatusCallback.getPlatform()+")与当前订单不匹配");
			logger.error("平台id("+orderStatusCallback.getPlatform()+")与当前订单第三方类型("+mainOrderInfoExtend.getThirdPartyType()+")不匹配");
			return resultMap;
		}
		
		//验证订单状态
		Integer orderStatusCbOld = mainOrderInfoExtend.getOrderStatusCallback();
		Map<String, Object> checkResult = checkOrderStatusCb(orderStatusCb, orderStatusCbOld);
		logger.info("订单状态验证结果:" + checkResult);
		if(!"0".equals(checkResult.get("resultcode"))){
			return checkResult;
		}
		
		if(2001 == orderStatusCb){
			logger.info("回调状态为联系不上用户, 不更新订单状态");
		}else {
			//更新订单信息
			updateOrderInfo(mainOrderInfo, mainOrderInfoExtend, orderStatusCallback);
		}
		
		resultMap.put("resultcode", "0");
		resultMap.put("resultdesc", "成功");
		return resultMap;
	}
	
	/**
	 * 更新订单信息
	 * @param mainOrderInfo
	 * @param mainOrderInfoExtend
	 * @param orderStatusCallback
	 */
	private void updateOrderInfo(MainOrderInfo mainOrderInfo, MainOrderInfoExtend mainOrderInfoExtend, 
			OrderStatusCallback orderStatusCallback){
		Date now = new Date();
		
		//主订单表update
		MainOrderInfo mainUpdate = new MainOrderInfo();
		mainUpdate.setId(mainOrderInfo.getId());
		mainUpdate.setOrderSendName(orderStatusCallback.getDeliverymanName());
		mainUpdate.setOrderSendPhone(orderStatusCallback.getDeliverymanPhone());
		mainUpdate.setUpdateTime(new Date());
		int result = orderStatusCbToMain(orderStatusCallback.getOrderStatus(), mainUpdate);
		if(result != 0){
			logger.error("订单状态转换异常 回调状态:" + orderStatusCallback.getOrderStatus());
			return;
		}
		
		//订单扩展表update
		MainOrderInfoExtend extendUpdate = new MainOrderInfoExtend();
		extendUpdate.setId(mainOrderInfoExtend.getId());
		extendUpdate.setDamage(orderStatusCallback.getDamage());
		extendUpdate.setDescCallback(orderStatusCallback.getDescription());
		extendUpdate.setOrderStatusCallback(orderStatusCallback.getOrderStatus());
		extendUpdate.setTradeOrderId(orderStatusCallback.getTradeOrderId());
		
		//订单状态更新任务
		OrderUpdateTask task = new OrderUpdateTask();
		task.setCreateTime(now);
		task.setCancelStatus(mainUpdate.getCancelStatus() != null ? mainUpdate.getCancelStatus() : mainOrderInfo.getCancelStatus());
		task.setGetStatus(mainUpdate.getGetStatus() != null ? mainUpdate.getGetStatus() : mainOrderInfo.getGetStatus());
		task.setGetTime(mainUpdate.getGetTime());
		task.setOrderId(mainOrderInfo.getOrderId());
		task.setOrderSendName(mainUpdate.getOrderSendName());
		task.setOrderSendPhone(mainUpdate.getOrderSendPhone());
		task.setOrderStatus(mainUpdate.getOrderStatus());
		task.setUpdateTime(now);
		task.setSendStatus(mainUpdate.getSendStatus() != null ? mainUpdate.getSendStatus() : mainOrderInfo.getSendStatus());
	
		//订单轨迹
		Ordertracks tracks = new Ordertracks();
		tracks.setComments(orderStatusCallback.getDescription());
		tracks.setCreateTime(now);
		tracks.setOrderId(mainOrderInfo.getOrderId());
		tracks.setOrderStatus(mainUpdate.getOrderStatus());
		tracks.setOrderOldStatus(mainOrderInfo.getOrderStatus());
		tracks.setUpdateTime(now);
		tracks.setzOrderId(extendUpdate.getTradeOrderId());
		tracks.setThirdPartyType(mainOrderInfoExtend.getThirdPartyType());
		tracks.setOperationContent(JSON.toJSONString(mainOrderInfo));
		tracks.setOperationName(orderStatusCallback.getPlatform());
		
		//保存数据
		mainOrderInfoService.update(mainUpdate);
		mainOrderInfoExtendService.update(extendUpdate);
		orderUpdateTaskService.save(task);
		ordertracksService.save(tracks);
		logger.info("更新订单信息成功");
	}
	
	/**
	 * 获取第三方平台类型
	 * @param platform
	 * @return
	 */
	public Integer getThirdPartyType(String platform){
		Integer pf = -1;
		if("kuainiu".equals(platform)){
			pf = 2;
		}else if("fengniao".equals(platform)){
			pf = 1;
		}else if("ecrowd".equals(platform)){
			pf = 0;
		}
		return pf;
	}
	
	/**
	 * 回调状态转为主订单状态
	 * 订单状态  1000  已派单, 1001  重新派单, 1002  开始配送, 1100  配送完成, 
	 * 2000  客户拒收, 2001  无法与客户联系, 2002  物品破损或丢失, 2100  配送失败
	 * @param orderStatusCb
	 * @return
	 */
	
	private int orderStatusCbToMain(Integer orderStatusCb, MainOrderInfo mainUpdate){
		int result = 0;
		Date now = new Date();
		//设置主订单状态
		switch(orderStatusCb){
			case 1000:  
				mainUpdate.setOrderStatus(26);  //派单成功
				mainUpdate.setAssignStatus(26);
				mainUpdate.setAssiginTime(now); break;
			case 1001:
				mainUpdate.setOrderStatus(26);  //派单成功
				mainUpdate.setAssignStatus(26);
				mainUpdate.setAssiginTime(now); break;
			case 1002:  
				mainUpdate.setOrderStatus(7);	//发货中
				mainUpdate.setGetStatus(37);	//取货成功
				mainUpdate.setGetTime(now);
				mainUpdate.setSendStatus(7); break;
			case 1100:
				mainUpdate.setOrderStatus(10);	//卖家已全部发货
				mainUpdate.setSendStatus(10); break;
			case 2000:
				mainUpdate.setOrderStatus(8);	//发货全部失败
				mainUpdate.setSendStatus(8); break;
			case 2001:
				mainUpdate.setOrderStatus(8);
				mainUpdate.setSendStatus(8); break;
			case 2002:
				mainUpdate.setOrderStatus(8);
				mainUpdate.setSendStatus(8); break;
			case 2100:
				mainUpdate.setOrderStatus(8);
				mainUpdate.setSendStatus(8); break;
			default:
				result = 1;
		}
		return result;
				
	}
	
	/**
	 * 验证订单状态
	 * @param orderStatusCb
	 * @param orderStatusCbOld
	 * @return
	 */
	private Map<String, Object> checkOrderStatusCb(Integer orderStatusCb, Integer orderStatusCbOld) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean result = true;
		switch(orderStatusCb){
			case 1000: 
				if(orderStatusCbOld != 0) result = false;
				break;
			case 1001:
				if(orderStatusCbOld == 1100) result = false;
				break;
			case 1002:
				if(orderStatusCbOld == 1100) result = false;
				break;
			case 1100:
				if(!(orderStatusCbOld == 1001 || orderStatusCbOld == 1002)) result = false;
				break;
			case 2000:
				if(orderStatusCbOld == 1100) result = false;
				break;
			case 2001:
				if(orderStatusCbOld == 1100) result = false;
				break;
			case 2002:
				if(orderStatusCbOld == 1100) result = false;
				break;
			case 2100:
				if(orderStatusCbOld == 1100) result = false;
				break;
			default: 
				result = false;
				resultMap.put("resultcode", "1");
				resultMap.put("resultdesc", "订单状态("+orderStatusCb+")不存在");
				return resultMap;
			
		}
		if(result){
			resultMap.put("resultcode", "0");
			resultMap.put("resultdesc", "订单状态正常");
		}else{
			resultMap.put("resultcode", "1");
			resultMap.put("resultdesc", "订单状态不能由"+orderStatusCbOld+"置为"+orderStatusCb);
		}
		return resultMap;

	}

	@Override
	public int save(OrderStatusCallback orderStatusCallback) {
		return orderStatusCallbackMapper.insertSelective(orderStatusCallback);
	}

}
