package com.pathaks.camel.route;

import com.pathaks.camel.beans.TestBean;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.toolbox.AggregationStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ExampleRoute extends RouteBuilder {

	@Autowired
	TestBean testBean;

	@Override
	public void configure() throws Exception {
		from("file:inbox")
		.split(body().tokenize("\n"))
			.setHeader("correlationKey").method(testBean, "twoChars(${body})")
			.log("${header.correlationKey}")
			.aggregate(header("correlationKey"), AggregationStrategies.bean(testBean, "append"))
				.completionTimeout(5000)
				.to("log:loggerName?showBody=false&showHeaders=true&showProperties=true&multiline=true")
				.to("file:outbox?fileName=${header.correlationKey}.txt");
	}
}