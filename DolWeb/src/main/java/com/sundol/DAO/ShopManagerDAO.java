package com.sundol.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import 	org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopManagerDAO extends SqlSessionDaoSupport {
	//	세션(컨텍션) 처리를 위해서 이미 <bean> 처리된 SqlSession을 자동 주입 시키자.
	@Autowired
	public SqlSessionTemplate	sqlSession;
	
	//	이 DAO는 보나마다 컨트롤러나 서비스에서 사용할 예정이므로
	//	그곳에서 자동 주입을 하기 위해서 <bean> 처리 해 놓는다.
	
	//	대카테고리 검색 질의 실행 함수
	public ArrayList getLCategory() {
		return (ArrayList) sqlSession.selectList("shopManager.selectLCate");
	}
	
	//	대카테고리 등록 질의 실행 함수
	public void insertLCategory(String lname) {
		sqlSession.insert("shopManager.LcateInsert", lname);
	}
	
	//	중카테고리 등록 질의 실행 함수
	public void insertMCategory(HashMap map) {
		sqlSession.insert("shopManager.McateInsert", map);
	}
	
	//	소카테고리 등록 질의 실행 함수
	public void insertSCategory(HashMap map) {
		sqlSession.insert("shopManager.ScateInsert", map);
	}
	
	//	중카테고리 검색 질의 실행 함수
	public ArrayList	selectMCategory(String lcode) {
		return (ArrayList) sqlSession.selectList("shopManager.selectMCate", lcode);
	}
}







