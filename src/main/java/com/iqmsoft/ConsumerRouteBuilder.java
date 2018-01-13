package com.iqmsoft;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.redis.processor.idempotent.RedisIdempotentRepository;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRouteBuilder extends RouteBuilder implements RoutesBuilder {

		
	@Override
	public void configure() throws Exception {
		
    	
    	errorHandler(deadLetterChannel("activemq:unique.dead").
    			maximumRedeliveries(1).redeliveryDelay(1000));
    	
    	
    	from("activemq:unique.order").
    		idempotentConsumer(header("uniqueId"),
    		MemoryIdempotentRepository.memoryIdempotentRepository(200)).
		to("activemq:magic.order");
    	
    	
		
	}
}