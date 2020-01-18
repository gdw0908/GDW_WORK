package com.gdw.cms.admin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdw.cms.common.dao.CommonDao;


@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private CommonDao  commonDao;
	
	@Override
	public List<HashMap<String, Object>> selectAdminList(HashMap<String, Object> param){
		List<HashMap<String, Object>> adminList = (List<HashMap<String, Object>>) commonDao.selectList("AdminSql.selectAdminList",param);
		return adminList;
	}
	
	@Override
	public HashMap<String, Object> selectAdminInfo(HashMap<String, Object> param){
		HashMap<String, Object> adminInfo = (HashMap<String, Object>) commonDao.selectOne("AdminSql.selectAdminInfo",param);
		return adminInfo;
	}
	
	@Override
	public int registAdminInfo(HashMap<String, Object> param) { 
		int update = (int)commonDao.update("AdminSql.registAdminInfo",param);
		return update;
	}
	
	@Override
	public int deleteAdminInfo(HashMap<String, Object> param) {
		int delete = (int)commonDao.update("AdminSql.deleteAdminInfo",param);
		return delete;
	}
}
