package com.gdw.cms.common.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDao {
	@Autowired
	@Resource(name="sqlSession")
	private SqlSessionTemplate  sqlsession;

	public Object selectOne(String statement) {
		return sqlsession.selectOne(statement);
	}
	
	public Object selectOne(String statement, Object parameter) {
		return sqlsession.selectOne(statement, parameter);
	}

	public Object selectOne(String statement, HashMap<String, Object> parameter) {
		return sqlsession.selectOne(statement, parameter);
	}

	public List<HashMap<String, Object>> selectList(String statement) {
		return sqlsession.selectList(statement);
	}

	public List<HashMap<String, Object>> selectList(String statement, Object parameter) {
		return sqlsession.selectList(statement, parameter);
	}

	public List<HashMap<String, Object>> selectList(String statement, HashMap<String, Object> parameter) {
		return sqlsession.selectList(statement, parameter);
	}

	public void insert(String statement) {
		sqlsession.insert(statement);
	}

	public void insert(String statement, Object parameter) {
		sqlsession.insert(statement, parameter);
	}

	public void insert(String statement, HashMap<String, Object> parameter) {
		sqlsession.insert(statement, parameter);
	}

	public Integer update(String statement) {
		return (Integer)sqlsession.update(statement);
	}

	public Integer update(String statement, Object parameter) {
		return (Integer)sqlsession.update(statement, parameter);
	}

	public Integer update(String statement, HashMap<String, Object> parameter) {
		return (Integer)sqlsession.update(statement, parameter);
	}

	public Integer delete(String statement) {
		return (Integer)sqlsession.delete(statement);
	}

	public Integer delete(String statement, Object parameter) {
		return (Integer)sqlsession.delete(statement, parameter);
	}

	public Integer delete(String statement, HashMap<String, Object> parameter) {
		return (Integer)sqlsession.delete(statement, parameter);
	}
}
