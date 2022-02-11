package com.microservices.netflixapigateway;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://CURENCY-EXCHANGE-SERVICE"))
				.route(p -> p.path("/currency-converter/**")
						.uri("lb://CURRENCY-CONVERSION-SERVICE"))
				.route(p -> p.path("/currency-converter-feign/**")
						.uri("lb://CURRENCY-CONVERSION-SERVICE"))
				.route(p -> p.path("/currency-conversion-new/**") 
						.filters(f -> f.rewritePath(
								"/currency-conversion-new/(?<segment>.*)", 
								"/currency-converter-feign/${segment}"))
						.uri("lb://currency-conversion-service"))
				.build();
	}

}
