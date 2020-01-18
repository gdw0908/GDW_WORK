package com.gdw.cms.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gdw.cms.common.auth.CustomAuthenticationProvider;
import com.gdw.cms.common.auth.CustomUserDetails;
import com.gdw.cms.common.auth.LoginHandler;
import com.gdw.cms.common.auth.UserInfo;
import com.gdw.cms.common.auth.UserInfoVO;
import com.gdw.cms.common.util.StringUtil;
import com.gdw.cms.login.service.LoginService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginHandler loginHandler;
	@Autowired
	private HttpServletRequest request; // Acquired by ReqeustContextListenr (web.xml)
	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login/login.dw")
	public ModelAndView loginPage(HttpServletRequest req, Model model) {
		logger.debug("login page");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(StringUtil.getViewName(req));
		return mav;
	}
	
	@RequestMapping(value = "/login/loginChk.dw")
	public @ResponseBody HashMap<String, Object> loginChk(@RequestParam HashMap<String,Object> reqMap, HttpSession session) {
		logger.debug("login chk");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String loginYn = null;
		//LoginHandler loginHandler = LoginHandler.getInstance();
		UserInfoVO userInfo = loginService.checkUsrLoginChk(reqMap);
		if(!StringUtils.isEmpty(userInfo)) {			
			if(!"R".equals(reqMap.get("loginDupleYn"))) {
				if(loginHandler.isUsing((String)reqMap.get("adminId"))){ //중복로그인체크
					resultMap.put("loginDupleYn", "Y");
					return resultMap;
				}else {
					resultMap.put("loginDupleYn", "N");
					request.getSession().setAttribute("loginUserInfo", userInfo);
					authProvider.buildUserFromAccount(userInfo);
					loginHandler.setSession(session, (String)reqMap.get("adminId"));
					loginYn = "Y";
				}
			}else {
				loginHandler.removeSession((String)reqMap.get("adminId"));
				request.getSession().setAttribute("loginUserInfo", userInfo);
				authProvider.buildUserFromAccount(userInfo);
				loginHandler.setSession(session, (String)reqMap.get("adminId"));
				loginYn = "Y";
			}
		}else {
			loginYn = "N";
		}
		resultMap.put("loginYn", loginYn);
		return resultMap;
	}

	@RequestMapping(value = "/login/logout.dw")
	public @ResponseBody boolean logout(@RequestParam HashMap<String,Object> reqMap, HttpSession session) {
//      session.removeAttribute("login"); // 하나씩 하려면 이렇게 해도 됨.
		//UserInfoVO userInfo = (UserInfoVO)session.getAttribute("loginUserInfo"); 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
		authorities.remove(new SimpleGrantedAuthority("ROLE_USER"));
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),authorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
        session.invalidate(); // 세션 전체를 날려버림
		return true;
	}
}
