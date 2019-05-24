package com.utils.tools.mq;

import com.utils.tools.mq.listener.testListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/*
* active 配置
* */
@Configuration
public class ActiveMQConfiguration {
    private static final Log log = LogFactory.getLog(ActiveMQConfiguration.class);

    @Value("${activemq.broker.url}")
    private String brokerUrl;

    @Value("${activemq.username}")
    private String username;

    @Value("${activemq.password}")
    private String password;

    @Value("${activemq.maxConnections}")
    private int maxConnections;

    /**
     * activeMQConnectionFactory:注入ActionMQ的连接工厂
     * @author CaoJian
     * @return
     */
    @Bean
    @Primary
    public ActiveMQConnectionFactory activeMQConnectionFactory () {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory ();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setUserName(username);
        activeMQConnectionFactory.setPassword(password);
        log.info("初始化ActiveMQ连接工厂，brokerUrl:"+brokerUrl);
        return activeMQConnectionFactory;
    }

    /**
     * pooledConnectionFactory:注入带连接池的连接工厂
     * @author CaoJian
     * @return
     */
    @Bean
    public PooledConnectionFactory pooledConnectionFactory () {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory ();
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory());
        pooledConnectionFactory.setMaxConnections(maxConnections);
        return pooledConnectionFactory;
    }

    /**
     * singleConnectionFactory:注入Spring的MQ连接工厂
     * @author CaoJian
     * @return
     */
    @Bean
    public SingleConnectionFactory singleConnectionFactory () {
        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory ();
        singleConnectionFactory.setTargetConnectionFactory(pooledConnectionFactory());
        return singleConnectionFactory;
    }

    /**
     * jmsTemplate:注入JmsTemplate
     * @author CaoJian
     * @return
     */
    @Bean
    public JmsTemplate jmsTemplate () {
        JmsTemplate jmsTemplate = new JmsTemplate ();
        jmsTemplate.setConnectionFactory(singleConnectionFactory());
        return jmsTemplate;
    }

    /**
     * activeMQUtils:注入自己封装的activeMQUtils工具类
     * @author CaoJian
     * @return
     */
    @Bean
    public ActiveMQUtils activeMQUtils () {
        ActiveMQUtils activeMQUtils = new ActiveMQUtils ();
        activeMQUtils.setJmsTemplate(jmsTemplate());
        return activeMQUtils;
    }


    /*********注解，监听监听器**********/
    @Bean(name = "jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(singleConnectionFactory());
        return factory;
    }
    /**********队列监听器*************************************/
	@Bean
	public testListener testQueueMessageListener () {
		testListener testListener = new testListener();
		testListener.setJmsTemplate(jmsTemplate ());
		return testListener;
	}
	@Bean
	public DefaultMessageListenerContainer testQueueMessageListenerContainer () {
		DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer ();
		defaultMessageListenerContainer.setConnectionFactory(singleConnectionFactory());
		defaultMessageListenerContainer.setDestinationName("NEW_ORDER_QUEUE");
		defaultMessageListenerContainer.setMessageListener(testQueueMessageListener());
		return defaultMessageListenerContainer;
	}


}
