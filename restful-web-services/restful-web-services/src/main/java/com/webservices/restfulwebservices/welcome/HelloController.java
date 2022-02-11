package com.webservices.restfulwebservices.welcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class HelloController {

	@Autowired
	MessageSource messageSource;

	@GetMapping("/welcome")
	public MappingJacksonValue greeting() {
		Welcome welcome=new Welcome("Welcome to seneca","Vidhya");
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("name");
		return filtering(filter,welcome);
	}
	
	
	public static MappingJacksonValue filtering(SimpleBeanPropertyFilter filter,Object object)
	{
		FilterProvider filterProvider=new SimpleFilterProvider().addFilter("DynamicFilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(object);
		mapping.setFilters(filterProvider);
		return mapping;
	}

	// returning bean
	@GetMapping("/welcome-bean")
	public Welcome greetingBean(@RequestParam("name") String name) {
		return new Welcome("Welcome to seneca",name);
	}

	@GetMapping("/welcome-bean/{name}")
	public Welcome greetingBeanPathVariable(@PathVariable String name)
	{
		return new Welcome("Welcome to seneca ", name);
	}

	
}
