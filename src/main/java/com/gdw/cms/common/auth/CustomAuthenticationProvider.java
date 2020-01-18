package com.gdw.cms.common.auth;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0,
			UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
	}

	@Override
	public UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authToken)
			throws AuthenticationException {

		String password = (String) authToken.getCredentials();
		try {
			UserInfo userInfo = null;//loginHandler.checkUsrLoginChk(username, password);
			return buildUserFromAccount(userInfo);
		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}

	public User buildUserFromAccount(UserInfo userInfo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),authorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		CustomUserDetails user = new CustomUserDetails(userInfo.getUsername(),
				userInfo.getPassword(), authorities, userInfo);
		return user;
	}
}
