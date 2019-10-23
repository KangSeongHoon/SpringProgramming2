package com.mycompany.web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.web.service.Ch09Service;

@Component
public class Ch09Dao {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Dao.class);

	public Ch09Dao() {
		logger.debug("Dao실행 ");
	}
	
	public void insert() {
		logger.debug("Dao.insert실행 ");
	}

}
