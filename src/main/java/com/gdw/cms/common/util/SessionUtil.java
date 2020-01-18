package com.gdw.cms.common.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gdw.cms.common.auth.UserInfoVO;

public class SessionUtil {
	
	public static UserInfoVO getSessionParam(HttpServletRequest req) {
        // login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴
		UserInfoVO userInfo = new UserInfoVO();
        userInfo = (UserInfoVO)req.getSession().getAttribute("loginUserInfo");
        return userInfo;
	}

}
