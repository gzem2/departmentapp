package com.gzem2.departmentapp.rest;

import com.gzem2.departmentapp.service.ServiceConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-mysql.properties")
@Import(value = {ServiceConfig.class})
@ComponentScan(basePackages = {"com.gzem2.departmentapp.rest"})
public class RestConfig {}
