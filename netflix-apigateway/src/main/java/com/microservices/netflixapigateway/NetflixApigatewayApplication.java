package com.microservices.netflixapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;


@EnableDiscoveryClient
@SpringBootApplication
public class NetflixApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixApigatewayApplication.class, args);
	}
	
	@Bean
	public Sampler defaultSampler()
	{
		return Sampler.ALWAYS_SAMPLE;
	}

}
