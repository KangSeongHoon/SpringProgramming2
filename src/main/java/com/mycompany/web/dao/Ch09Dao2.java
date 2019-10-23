package com.mycompany.web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.web.service.Ch09Service;

@Component
public class Ch09Dao2 {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Dao2.class);
	
	public Ch09Dao2() {
		logger.debug("Dao실행 ");
	}	
	
	public void insert() {
		logger.debug("실행 ");
	}

}
