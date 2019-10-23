package com.mycompany.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mycompany.web.dao.Ch09Dao;

@Service

public class Ch09Service {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service.class);
	@Autowired
	private Ch09Dao ch09Dao;
	
	public Ch09Service(String arg1, int arg2){
		
	}
	
	public void method1() {
		logger.debug("service method1 실행");
		ch09Dao.insert();
		
	}

}
