package com.gdw.cms.login.service;

import java.util.HashMap;

import com.gdw.cms.common.auth.UserInfoVO;

public interface LoginService {
	public HashMap<String, Object> selectAdminChk(HashMap<String, Object> param);
	public UserInfoVO checkUsrLoginChk(HashMap<String, Object> param);
}
