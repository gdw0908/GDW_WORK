package com.gdw.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdw.cms.common.dao.CommonDao;

@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	private CommonDao  commonDao;
	
	@Override
	public List selectList(Map param){
		List list = commonDao.selectList("HomeSql.selectList",param);
		return list;
	}
}
