package com.templates.usecases.config;

import javax.jms.ConnectionFactory;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
@EnableJms
@EnableConfigurationProperties(ActiveMQProperties.class)	// Used when config ActiveMQ
public class AmqMessageConfig {
	
	@Value("${config.jms.brokerA.url}")
	private String brokerAUrl;

	@Value("${config.jms.brokerA.user}")
	private String brokerAUser;	
	
	@Value("${config.jms.brokerA.password}")
	private String brokerAPassword;	
	
	
	// To configure details of connection between client and a JMS provider's broker   ( WebSphereMQ / ActiveMQ ?? etc )
	@Bean("AmqBrokerAConnectionFactory")
	public ConnectionFactory getAmqBrokerAConnectionFactory() {
		ActiveMQConnectionFactory qcf = new ActiveMQConnectionFactory(brokerAUser, brokerAPassword, brokerAUrl);
		return qcf;
	}
	
	// A helper class that simplifies receiving and sending of message to avoid boilerplate code
	@Bean
	@Qualifier
	public JmsTemplate AmqJmsTemplate(@Qualifier("AmqBrokerAConnectionFactory") ConnectionFactory cf){
		JmsTemplate brokerATemplate = new JmsTemplate(cf);
		brokerATemplate.setSessionTransacted(true);
		return brokerATemplate;
	}
	
	// It creates a listener container responsible for an endpoint ( or broker )
	@Bean("AmqBrokerAJmsListenerContainerFactory")
	public JmsListenerContainerFactory<DefaultMessageListenerContainer> getAmqBrokerAJmsListenerContainerFactory(
				@Qualifier("AmqBrokerAConnectionFactory") ConnectionFactory cf) {
		DefaultJmsListenerContainerFactory amqLcf = new DefaultJmsListenerContainerFactory();
		amqLcf.setConnectionFactory(cf);
		amqLcf.setSessionTransacted(true);
		return amqLcf;
	}
	
	/*
	// This creates local AMQ service 
	@Bean
	public BrokerService broker() throws Exception {
	    BrokerService broker = new BrokerService();
	    broker.addConnector(brokerAUrl);
	    broker.setPersistent(false);
	    return broker;
	}
	*/

}

