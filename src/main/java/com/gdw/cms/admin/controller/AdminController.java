package com.gdw.cms.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gdw.cms.admin.service.AdminService;
import com.gdw.cms.common.auth.UserInfoVO;
import com.gdw.cms.common.util.PageUtil;
import com.gdw.cms.common.util.SessionUtil;
import com.gdw.cms.common.util.StringUtil;

@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	/**
	 * 관리자 관리 리스트
	 * @param reqMap
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/admin/adminList.dw")
	public ModelAndView adminList(@RequestParam HashMap<String,Object> reqMap,  HttpServletRequest req) {
		logger.debug("admin manage page");
		ModelAndView mav = new ModelAndView();
		
		List<HashMap<String, Object>> resultList =adminService.selectAdminList(reqMap);
		PageUtil pagination = new PageUtil(resultList.size(), Integer.parseInt(StringUtil.nvl((String)reqMap.get("page"),"1")) );

		reqMap.put("page", (Integer.parseInt(StringUtil.nvl((String)reqMap.get("page"),"1"))-1)*10);
		reqMap.put("row", pagination.getPageSize());
		resultList =adminService.selectAdminList(reqMap);
		
		mav.addObject("list", resultList);
		mav.addObject("param", reqMap);
		mav.addObject("pagination", pagination);		
		mav.setViewName(StringUtil.getViewName(req));
		return mav;
	}
	
	/**
	 * 관리자 관리 등록 및 수정화면
	 * @param reqMap
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/admin/adminRegist.dw")
	public ModelAndView adminRegist(@RequestParam HashMap<String,Object> reqMap, HttpServletRequest req) {
		logger.debug("admin adminRegist page");
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> resultInfo =adminService.selectAdminInfo(reqMap); 
		mav.addObject("item", resultInfo);
		mav.setViewName(StringUtil.getViewName(req));
		return mav;
	}
	
	/**
	 * 관리자 등록, 수정
	 * @param reqMap
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/admin/registAdminInfo.dw")
	public @ResponseBody HashMap<String, Object> registAdminInfo(@RequestParam HashMap<String,Object> reqMap, HttpServletRequest req) {
		logger.debug("admin registAdminInfo page");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		UserInfoVO userInfo = SessionUtil.getSessionParam(req);
        reqMap.put("regUserid", userInfo.getAdminId());
        reqMap.put("modUserid", userInfo.getAdminId());
		int result = adminService.registAdminInfo(reqMap);
		if(result > 0) {
			resultMap =adminService.selectAdminInfo(reqMap);
			resultMap.put("resultCode", "0000");
		}
		return resultMap;
	}
	
	/**
	 * 관리자 삭제
	 * @param reqMap
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/admin/deleteAdminInfo.dw")
	public @ResponseBody HashMap<String, Object> deleteAdminInfo(@RequestParam HashMap<String,Object> reqMap, HttpServletRequest req) {
		logger.debug("admin deleteAdminInfo page");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int result = adminService.deleteAdminInfo(reqMap);
		if(result > 0) {
			resultMap.put("resultCode", "0000");
		}
		return resultMap;
	}
	
}
