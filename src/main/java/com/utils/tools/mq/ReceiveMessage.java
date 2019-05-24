package com.utils.tools.mq;


import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


/**
 * ActiveMq信息监听
 */
@Service
@EnableJms
public class ReceiveMessage extends MessageListenerAdapter {


	@Override
	@JmsListener(destination="NEW_ORDER_QUEUE_Test")
	public void onMessage(Message message, Session session) throws JMSException {
		System.out.println("22222222");
		System.out.println(message);
	}
}
