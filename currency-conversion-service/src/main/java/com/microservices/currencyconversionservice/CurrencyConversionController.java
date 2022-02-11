package com.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@Autowired
	private Environment environment;
	

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	@Retry(name = "sample-api", fallbackMethod = "fallbackResponse")
	public Object convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		log.info("fetched results");
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://"+ environment.getProperty("host")+":"+environment.getProperty("port")+"/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		
		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

	// feign
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

	public String fallbackResponse(Exception ex) {
		return"Exchange Service is down";
	}
	
	

}
