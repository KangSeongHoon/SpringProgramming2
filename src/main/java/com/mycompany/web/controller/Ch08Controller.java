package com.mycompany.web.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ch08")
public class Ch08Controller {

	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);

	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}

	@PostMapping("/fileUpload")
	public String fileUpload(String title, String description, 
			MultipartFile attach1, MultipartFile attach2 ,HttpServletRequest request, Model model) throws Exception {
		logger.debug(title);
		logger.debug(description);
		ServletContext application = request.getServletContext();
		String savePath = application.getRealPath("/resources/upload/");
		if(!attach1.isEmpty()) {
			logger.debug("----------------------");
			logger.debug("attach1 :" + attach1.getOriginalFilename());
			logger.debug("attach1 :" + attach1.getContentType());
			logger.debug("attach1 :" + attach1.getSize());
			String savefileName = new Date().getTime() + "-" + 
			attach1.getOriginalFilename();
			
			logger.debug("attach1 :" + savefileName);
			attach1.transferTo(new File(savePath + savefileName));
		
		}

		if(!attach2.isEmpty()) {
			logger.debug("----------------------");
			logger.debug("attach2 :" + attach2.getOriginalFilename());
			logger.debug("attach2 :" + attach2.getContentType());
			logger.debug("attach2 :" + attach2.getSize());
			
			String savefileName = new Date().getTime() + "-" + 
			attach2.getOriginalFilename();
			
			//저장하는 메서드
			logger.debug("attach2 :" + savefileName);
			attach2.transferTo(new File(savePath + savefileName));
		}

		model.addAttribute("title", title);
		model.addAttribute("description",description);
		if(!attach1.isEmpty()) {
			model.addAttribute("attach1", attach1.getOriginalFilename());
		}
		if(!attach2.isEmpty()) {
			model.addAttribute("attach2", attach2.getOriginalFilename());
		}
		
		
		
		return "ch08/fileUpload";
	}
}
