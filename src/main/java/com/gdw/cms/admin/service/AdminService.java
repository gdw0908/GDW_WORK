package com.gdw.cms.admin.service;

import java.util.HashMap;
import java.util.List;

public interface AdminService {
	public List<HashMap<String, Object>> selectAdminList(HashMap<String, Object> param);
	
	public HashMap<String, Object> selectAdminInfo(HashMap<String, Object> param);
	
	public int registAdminInfo(HashMap<String, Object> param);
	
	public int deleteAdminInfo(HashMap<String, Object> param);
	
}
