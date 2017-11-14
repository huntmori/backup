package com.sundol.Service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.DAO.LoginDAO;

/*
 * 	�� Ŭ������ ��Ʈ�ѷ� ��ſ� �����Ͻ� ������ ����� Ŭ�����̴�.
 * 	��, Ư�� �۾��� ������ �� 
 * 	��Ʈ�ѷδ� �Ķ���� �ް�, �� ȣ�� �κи� ó���ϴ� ���� ��Ģ�̰�
 * 	������ ���� �κ��� ���� Ŭ������ �̿��ؼ� ó���ϴ� ���� ��Ģ�̴�.
 */
public class LoginService {

	@Autowired
	public LoginDAO	lDao;
	
	//	�α��� ó���� �ϱ� ���� ���� �Լ�
	public int loginProc(String id, String pw) {
		//	����
		//		�����ͺ��̽��� �̿��ؼ� ȸ������ Ȯ���ϰ�
		//		�� ����� ��Ʈ�ѷ��� �˷��ش�.
		//	
		//	�� �κп��� �տ��� ����� ���� DAO�� �̿��ؾ� �� ���̴�.
		//	�̶� ����� ���� DAO�� DI ����� �̿��ؼ� �ڵ� �����ϴ� ���� ����.
		
		//	�ڵ� ������ �ϱ� ���ؼ���
		//	1.	xml ���Ͽ� ����� Ŭ������ <bean> ó���Ѵ�.
		//	2.	@������̼��� �̿��ؼ� �ڵ� �����ؼ� ����Ѵ�.
		
		HashMap	map = new HashMap();
		map.put("nick", id);
		map.put("password", pw);
		
		int	result = lDao.loginProc(map);
		return result;
	}
}








