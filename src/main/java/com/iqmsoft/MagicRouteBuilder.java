package com.iqmsoft;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import org.springframework.stereotype.Component;


@Component
public class MagicRouteBuilder extends RouteBuilder implements RoutesBuilder {
	
	public static String splitXpath = "//orders/order";

    public void configure() {
    	
    	
    	errorHandler(deadLetterChannel("activemq:queue:emagic.dead"));
    	
		XPathBuilder splitXPath = new XPathBuilder (splitXpath);
		
    
		from("activemq:emagic.orders").
			split(splitXPath).parallelProcessing().
		to("activemq:emagic.order");
    	
    	
    	from("activemq:emagic.order").
    	choice().
    		when().simple("${in.body} contains 'Houdini'").
    			to("activemq:priority.order").
    		otherwise().
    			to("activemq:magic.order");
    	
    	
    }
}