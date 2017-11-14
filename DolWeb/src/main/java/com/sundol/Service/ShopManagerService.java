package com.sundol.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.DAO.ShopManagerDAO;
import com.sundol.VO.ShopManagerVO;

public class ShopManagerService {
	//	�� Ŭ������ �������� DAO�� ����ؼ� �����Ͻ� ������ ó���� �����̴�.
	//	�̹� ����� ���� DAO�� �ڵ� ���� ������� ����ϵ��� ��ġ����.
	
	@Autowired
	private	ShopManagerDAO		smDAO;
	//	���Ľ�
	//		@Autowired�� Ŭ���� �̸��� ������ �ڵ� �����ϰ� �ȴ�.
	//		�׷��Ƿ� ���� �̸��� �ƹ����谡 ����.
	
	//	�� ���� Ŭ���� ���� ��Ʈ�ѷ����� ����� �����̹Ƿ�
	//	�װ������� �ڵ� ������ �����ϵ��� <bean> ó�� �� ����.
	
	/*
	 * 	��ī�װ� �˻� ó�� �Լ�
	 */
	public ArrayList getLCategory() {
		return smDAO.getLCategory();
	}
	
	/*
	 * 	��ī�װ� ��� ó�� �Լ�
	 */
	public void insertLCategory(String lname) {
		smDAO.insertLCategory(lname);
	}
	
	/*
	 * 	��ī�װ� ��� ó�� �Լ�
	 */
	public void insertMCategory(ShopManagerVO vo) {
		//	vo �����߿��� ���ǿ� �ʿ��� �����͸� Map���� ����
		
		HashMap map = new HashMap();
		map.put("lcate", vo.getLcode());
		map.put("mname", vo.getMname());
		
		smDAO.insertMCategory(map);
	}
	
	/*
	 * 	��ī�װ� ��� ó�� �Լ�
	 */
	public void insertSCategory(ShopManagerVO vo) {
		//	vo �����߿��� ���ǿ� �ʿ��� �����͸� Map���� ����
		
		HashMap map = new HashMap();
		map.put("mcate", vo.getLcode());
		map.put("sname", vo.getSname());
		
		smDAO.insertSCategory(map);
	}

	/*
	 * 	��ī�װ� �˻� ó�� �Լ�
	 */
	public ArrayList selectMCate(String lcode) {
		return smDAO.selectMCategory(lcode);
	}
}










