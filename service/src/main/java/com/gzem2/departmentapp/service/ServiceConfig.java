package com.gzem2.departmentapp.service;

import com.gzem2.departmentapp.dao.DaoConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {DaoConfig.class})
@ComponentScan(basePackages = {"com.gzem2.departmentapp.service"})
public class ServiceConfig {}
