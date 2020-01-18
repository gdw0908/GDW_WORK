package com.gdw.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdw.cms.common.dao.CommonDao;

public interface HomeService {
	public List selectList(Map param);
}
