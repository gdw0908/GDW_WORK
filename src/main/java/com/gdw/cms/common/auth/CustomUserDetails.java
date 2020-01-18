package com.gdw.cms.common.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
	private static final long serialVersionUID = 2524336940024122718L;	
	UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public CustomUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities, UserInfo userInfo) {
		super(username, password, authorities);
		setUserInfo(userInfo);
	}
}
