package com.wgn.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
  
public class KafkaConsumerListener implements MessageListener<Integer, String> {
    @Override
    public void onMessage(ConsumerRecord<Integer, String> record) {
    	System.out.println("hahahaha");
        System.out.println(record);
    }
}