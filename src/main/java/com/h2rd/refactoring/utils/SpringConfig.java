package com.h2rd.refactoring.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.h2rd.refactoring.usermanagement", "com.h2rd.refactoring.utils", 
		"com.h2rd.refactoring.web"})
public class SpringConfig {
	

}
