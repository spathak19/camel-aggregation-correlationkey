package com.pathaks.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class ExampleRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("timer:foo?repeatCount=1")
		.log("test message");
	}
}