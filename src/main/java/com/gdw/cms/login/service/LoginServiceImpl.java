package com.gdw.cms.login.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdw.cms.common.auth.UserInfoVO;
import com.gdw.cms.common.dao.CommonDao;
@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private CommonDao  commonDao;
	
	@Override
	public HashMap<String, Object> selectAdminChk(HashMap<String, Object> param) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> userInfo = (HashMap<String, Object>) commonDao.selectOne("LoginSql.selectAdminChk",param);
		return userInfo;
	}
	@Override
	public UserInfoVO checkUsrLoginChk(HashMap<String, Object> param) {
		UserInfoVO userInfo = (UserInfoVO) commonDao.selectOne("LoginSql.checkUsrLoginChk",param);
		return userInfo;
	}
	
}
