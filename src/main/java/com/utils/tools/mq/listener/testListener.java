package com.utils.tools.mq.listener;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.Message;
import javax.jms.Session;
import java.util.Map;

public class testListener implements SessionAwareMessageListener<Message> {
    private static final Log log = LogFactory.getLog(testListener.class);

    @SuppressWarnings("unused")
    private JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public testListener() {
        log.info("队列： 监听器初始化成功，开始接收消息...");
    }

    @SuppressWarnings("unchecked")
    public synchronized void onMessage(Message message, Session session) {
        try {
            ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
            final String ms = msg.getText();
            log.info("==>receive message:" + ms);
            Map<String,Object> msgMap = JSONObject.parseObject(ms,Map.class);
            String messageId = (String) msgMap.get("messageId");
            String messageBody = (String) msgMap.get("messageBody");

            System.out.println("22222222222222222222222222222222");

        } catch (Exception e) {
            log.error("==>Exception", e);
        }
    }
}
