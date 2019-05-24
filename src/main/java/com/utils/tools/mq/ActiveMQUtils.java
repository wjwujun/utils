/**
 * @Project Name:sys-web
 * @File Name:ActiveMQUtils.java
 * @Package Name:com.shundian.yuanben.common.mq
 * @Date:2016年12月28日下午3:18:28
 * @author:CaoJian
 * @Copyright (c) 2016, www.ybveg.com All Rights Reserved.
 */
package com.utils.tools.mq;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * ActiveMQ工具类
 * @ClassName: ActiveMQUtils 
 * @date: 2016年12月28日 下午3:18:28 
 * @author CaoJian
 */
public class ActiveMQUtils {
	
	private static final Logger log = LoggerFactory.getLogger(ActiveMQUtils.class);
	
	private JmsTemplate jmsTemplate;
	
	/**
	 * sendMessage:发送消息
	 * @author CaoJian
	 * @param destinationName 队列名称
	 * @param obj 消息对象
	 */
	public void sendMessage (String destinationName, Object obj) {
		log.info("发送消息到ActiveMQ，队列："+destinationName+"，消息对象："+obj.getClass());
		System.out.println(destinationName);
		System.out.println(obj);

		jmsTemplate.send(destinationName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(JSONObject.toJSONString(""));
			}
		});
	}

	/**
	 * sendStringMessage:发送消息
	 * @author CaoJian
	 * @param destinationName
	 * @param jsonString
	 */
	public void sendStringMessage (String destinationName, String jsonString) {
		log.info("发送消息到ActiveMQ，队列："+destinationName+"，消息内容："+jsonString);
		jmsTemplate.send(destinationName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("");
			}
		});
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}

