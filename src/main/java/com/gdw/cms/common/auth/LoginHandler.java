package com.gdw.cms.common.auth;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.gdw.cms.login.service.LoginService;

public class LoginHandler implements HttpSessionBindingListener {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginHandler.class);
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private HttpServletRequest request; // Acquired by ReqeustContextListenr (web.xml)
	
	public static Hashtable loginUsers = new Hashtable();
	
	private static LoginHandler loginHandler = null;
	
	public static synchronized LoginHandler getInstance() {
		if(loginHandler == null) {
			loginHandler = new LoginHandler();
		}
		return loginHandler;
	}
	
	//세션이 셋팅할때 호출된다(session.setAttribute())
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		loginUsers.put(event.getSession(), event.getName()+"|"+(String)request.getParameter("_csrf"));
		logger.debug(event.getName() + "님이 로그인 하셨습니다.");
		logger.debug("현재 접속자 수 : "+getUserCount());
	}

	//세션이 끊겻을때 호출된다(invalidate)
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		loginUsers.remove(event.getSession());
		logger.debug(event.getName() + "님이 로그아웃 하셨습니다.");
		logger.debug("현재 접속자 수 : "+getUserCount());
	}
	
	//사용자 아이디를 세션에 저장
	public void setSession(HttpSession session, String userId) {
		session.setAttribute(userId, this);		
	}
	
	//입력받은 아이디를 해시테이블 삭제
	public void removeSession(String userId) {
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		while(e.hasMoreElements()) {
			session = (HttpSession)e.nextElement();
			if(loginUsers.get(session).toString().indexOf(userId) > -1) {
				String[] userInfo = loginUsers.get(session).toString().split("\\|");
				if(!userInfo[1].equals((String)request.getParameter("_csrf"))) {
					session.invalidate();					
				}
			}
		}
	}
	
	//아이디 , 패스워드 체크 
	public boolean isValid(String userId, String passwd) {
		HashMap<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("adminId", userId);
		reqMap.put("adminPw", passwd);
		UserInfoVO userInfo = loginService.checkUsrLoginChk(reqMap);
		if(!StringUtils.isEmpty(userInfo)) {
			return true;
		}else {
			return false;
		}
	}
	
	//이미 사용중인 아이디 체크
	public boolean isUsing(String userId) {
		logger.debug("this.userID="+getUserID(request.getSession()));
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		while(e.hasMoreElements()) {
			session = (HttpSession)e.nextElement();
			if(loginUsers.get(session).toString().indexOf(userId) > -1) {
				return true;
			}
		}
		return false;
		//return loginUsers.contains(userId);
	}
	
	//현재 세션의 아이디를 리턴
	public String getUserID(HttpSession session) {
		return (String)loginUsers.get(session);
	}
	
	//현재 접속자 수
	public int getUserCount() {
		return loginUsers.size();
	}
}
