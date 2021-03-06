package com.gdw.cms.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gdw.cms.common.util.StringUtil;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/main/home.dw")
	public ModelAndView home(HttpServletRequest req, Model model) {
		logger.debug("main page");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(StringUtil.getViewName(req));
		return mav;
	}
}
