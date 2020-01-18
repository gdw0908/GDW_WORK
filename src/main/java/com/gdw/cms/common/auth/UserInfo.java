package com.gdw.cms.common.auth;

import java.io.Serializable;

public interface UserInfo extends Serializable {
	
	public String getUsername();
	public String getPassword();
}
