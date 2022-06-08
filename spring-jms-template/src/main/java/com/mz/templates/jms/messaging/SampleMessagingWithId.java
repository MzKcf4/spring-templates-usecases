package com.mz.templates.jms.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SampleMessagingWithId {
	
	@Value("${config.jms.secondQueue}")
	private String secondQueueName;
	
	@Autowired
	@Qualifier("AmqJmsTemplate")
	JmsTemplate jmsTemplate;
	
	
	public void sendAndReceive(String messageId){
		String messageBody = "SampleMessageBody";
		
		MessageCreator messageCreator = SampleMessageCreator.builder()
				.messageBody(messageBody)
				.messageId(messageId)
				.build();
		
		jmsTemplate.convertAndSend(secondQueueName, messageCreator);
		
		manualListenMessage(messageId);
	}
	
	// This will only listen to message with specific messageId in header-key : JMS_MESSAGE_ID
	public void manualListenMessage(String targetMessageId) {
		String messageSelector = getMessageSelector(JmsHeaders.MESSAGE_ID, targetMessageId);
		jmsTemplate.setReceiveTimeout(10000L);
		jmsTemplate.receiveSelected(secondQueueName , messageSelector);
		
		log.info("Received message with target message id : {}" , targetMessageId);
	}
	
	private String getMessageSelector(String header , String value) {
		return header + "=" + value;
	}
}
