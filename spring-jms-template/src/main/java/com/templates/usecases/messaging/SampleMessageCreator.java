package com.templates.usecases.messaging;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleMessageCreator implements MessageCreator , Serializable{
	
	private static final long serialVersionUID = -2821112519839847882L;
	private Map<String, Object> customHeaderMap;
	private String messageId;
	private String messageBody;
	
	@Override
	public Message createMessage(Session session) throws JMSException {
		Message message = session.createTextMessage(messageBody);
		
		if(StringUtils.isNotBlank(messageId)) {
			message.setJMSMessageID(messageId);
		}
		
		if(!CollectionUtils.isEmpty(customHeaderMap)) {
			for (Entry<String, Object> entry : customHeaderMap.entrySet()) {
				message.setObjectProperty(entry.getKey(), entry.getValue());
			}
		}
		
		return message;
	}

}
