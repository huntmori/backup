package com.sundol.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import 	org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopManagerDAO extends SqlSessionDaoSupport {
	//	����(���ؼ�) ó���� ���ؼ� �̹� <bean> ó���� SqlSession�� �ڵ� ���� ��Ű��.
	@Autowired
	public SqlSessionTemplate	sqlSession;
	
	//	�� DAO�� �������� ��Ʈ�ѷ��� ���񽺿��� ����� �����̹Ƿ�
	//	�װ����� �ڵ� ������ �ϱ� ���ؼ� <bean> ó�� �� ���´�.
	
	//	��ī�װ� �˻� ���� ���� �Լ�
	public ArrayList getLCategory() {
		return (ArrayList) sqlSession.selectList("shopManager.selectLCate");
	}
	
	//	��ī�װ� ��� ���� ���� �Լ�
	public void insertLCategory(String lname) {
		sqlSession.insert("shopManager.LcateInsert", lname);
	}
	
	//	��ī�װ� ��� ���� ���� �Լ�
	public void insertMCategory(HashMap map) {
		sqlSession.insert("shopManager.McateInsert", map);
	}
	
	//	��ī�װ� ��� ���� ���� �Լ�
	public void insertSCategory(HashMap map) {
		sqlSession.insert("shopManager.ScateInsert", map);
	}
	
	//	��ī�װ� �˻� ���� ���� �Լ�
	public ArrayList	selectMCategory(String lcode) {
		return (ArrayList) sqlSession.selectList("shopManager.selectMCate", lcode);
	}
}







