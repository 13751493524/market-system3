package com.cn.kafka.producer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaComsumer {
	private final KafkaTemplate kafkaTemplate;

	@Autowired
	public KafkaComsumer(KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@KafkaListener(topics = "someTopic")
	public void processMessage(String content) {
		System.out.println("========content=============>"+content);
		System.out.println("========content=============>"+content);
		System.out.println("========content=============>"+content);
		System.out.println("========content=============>"+content);
	}
	
	public void sendMessage(String content){
		kafkaTemplate.send("someTopic", "user", "{\"createDate\":\"2019-05-28\",\"id\":1,\"name\":\"guojianbin\"}");
	}
}
