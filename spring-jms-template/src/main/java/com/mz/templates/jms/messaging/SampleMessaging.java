package com.mz.templates.jms.messaging;

import java.util.Map;

import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsMessageHeaderAccessor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/*
 * Standard JmsHeaders : 
JMSDestination �V the destination where the message is sent.
JMSReplyTo �V the JMS destination where the reply message should be sent.
JMSDeliveryMode �V the delivery mode of the message. can be one of the following:
PERSISTENT �V signifies the messages are stored and forwarded
NON_PERSISTENT �V messages are not stored and may be lost due to failures in transmission.
JMSMessageID �V the unique ID of the message.
JMSTimestamp �V the time a message was handed off to a JMS provider to be sent. The time expressed at the amount of time, in milliseconds.
JMSExpiration �V the expiration time of the message.
JMSRedelivered �V typically this item is set when the JMS provider has delivered the message at least once before.
JMSPriority �V the priority of the message. Priority is a value from 0-9. Higher numbers signify a higher priority (that is, 9 is a higher priority than 8).
JMSCorrelationID �V this ID is used to link a response message with its related request message. This is usually the message ID of a request message when this field is found in a reply message.
JMSType �V the JMS provider-supplied string to describe the type of the message. Some JMS providers use this property to define messages in the provider��s repository. See the JMS provider documentation for more information about the use of this field.
 * 
 * */


@Slf4j
@Component
public class SampleMessaging {
	
	@Value("${config.jms.firstQueue}")
	private String firstQueueName;
	
	@Autowired
	@Qualifier("AmqJmsTemplate")
	JmsTemplate jmsTemplate;
	
	public void sendRequest(){
		String messageBody = "SampleMessageBody";
		jmsTemplate.convertAndSend(firstQueueName, messageBody);
	}
	
	// Taken from : https://memorynotfound.com/spring-jms-setting-reading-header-properties-example/
	@JmsListener(containerFactory = "AmqBrokerAJmsListenerContainerFactory", destination = "${config.jms.firstQueue}")
    public void receiveMessage(@Payload TextMessage message,
                               // @Header(JmsHeaders.CORRELATION_ID) String correlationId,			// <-- Mandatory header field , will throw exception if not defined
                               @Header(name = "jms-header-not-exists", defaultValue = "default") String nonExistingHeader,
                               @Headers Map<String, Object> headers,
                               MessageHeaders messageHeaders,
                               JmsMessageHeaderAccessor jmsMessageHeaderAccessor) {

        log.info("received <" + message + ">");

        log.info("\n# Spring JMS accessing single header property");
        // log.info("- jms_correlationId=" + correlationId);
        log.info("- jms-header-not-exists=" + nonExistingHeader);

        log.info("\n# Spring JMS retrieving all header properties using Map<String, Object>");
        log.info("- jms-custom-header=" + String.valueOf(headers.get("jms-custom-property")));
    	
        // Recommended to use messageHeaders + jmsMessageHeaderAccessor combination for accessing headers.
        log.info("\n# Spring JMS retrieving all header properties MessageHeaders");
        log.info("- jms-custom-property-price=" + messageHeaders.get("jms-custom-property-price", Double.class));

        log.info("\n# Spring JMS retrieving all header properties JmsMessageHeaderAccessor");
        log.info("- jms_destination=" + jmsMessageHeaderAccessor.getDestination());
        log.info("- jms_priority=" + jmsMessageHeaderAccessor.getPriority());
        log.info("- jms_timestamp=" + jmsMessageHeaderAccessor.getTimestamp());
        log.info("- jms_type=" + jmsMessageHeaderAccessor.getType());
        log.info("- jms_redelivered=" + jmsMessageHeaderAccessor.getRedelivered());
        log.info("- jms_replyTo=" + jmsMessageHeaderAccessor.getReplyTo());
        log.info("- jms_correlationId=" + jmsMessageHeaderAccessor.getCorrelationId());
        log.info("- jms_contentType=" + jmsMessageHeaderAccessor.getContentType());
        log.info("- jms_expiration=" + jmsMessageHeaderAccessor.getExpiration());
        log.info("- jms_messageId=" + jmsMessageHeaderAccessor.getMessageId());
        log.info("- jms_deliveryMode=" + jmsMessageHeaderAccessor.getDeliveryMode() + "\n");

    }
}
