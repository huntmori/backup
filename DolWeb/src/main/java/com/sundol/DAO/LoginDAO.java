package com.sundol.DAO;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import 	org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * 	�� Ŭ������ myBatis�� �̿��ؼ� ���� ����� ������ ������ DAO Ŭ������ �ǰڴ�.
 * 
 * 	DAO Ŭ������ �Ǳ� ���� �ʼ� ����
 * 
 * 		�ݵ�� SqlSessionDaoSupport	Ŭ������ ��� �޾Ƽ� �����ؾ� �Ѵ�.
 * 
 * 		SqlSessionDaoSupport�� myBatis�� ������ �ִ� ���ؼ��� �����ϴ� Ŭ�����̴�.
 * 
 */
public class LoginDAO extends SqlSessionDaoSupport {

	//	���� ����� ������ �� ���� �߿��� ���� ��� ���ؼ��̴�.
	//	�츮�� myBatis�� ȯ�� ������ ����� �� �̹� �� ���ؼ��� <bean> ó�� �� ���Ҵ�.
	//	�׷ιǷ� �ڵ� ���� ��Ŀ� ���ؼ� ���ؼ��� new ���Ѽ� ����Ѵ�.
	
	//	�ڵ����� �� Autowired�� <bean>ó���� ���� �߿��� Ŭ�����̸��� ������ ã�ƿ��� ����̴�.
	//				  Resouecr�� id�� �̿��ؼ� ã�ƿ��� ����̴�.
	@Autowired
	public SqlSessionTemplate	sqlSession;
	
	//	���Ǹ� ������ �Լ��� ��������.
	public int loginProc(HashMap map) {
		//	���� ����� �����ϴ� ���
		//	�̹� ���ؼǰ� �ʿ��� ��ġ�� myBatis�� �� ���ұ� ������ ���ุ �ϸ� �ȴ�.
		//	���� �����	���� ������ ���� ���ݾ� �޶�����.
		//		��	���� ����� SELECT�̸� selectXXX �Լ��� �̿��ϸ� �ǰ�
		//			���� ����� UPDATE�̸� updateXXX �Լ��� �̿��ϸ� �ȴ�.
		
		//	����
		//		��� �Լ��� 1�� �Ķ���ʹ� String�̴�.
		//		�̰��� �ϴ� ��Ȱ�� �ٷ� ������ ���� ����� �����ϴ� ����̴�.
		//		���� ��� �������
		//			���� ����� ���Ե� SQL ������ namespace.���Ǹ�ɿ� �ο��� id
		//		��>
		//			�츮�� ������ ���� �����		login	�̶�� namespace�� �ִ� ����
		//							���� ��� id		login	�̴�.
		//			��������� ���� ��� �ڵ�		login.login	��������.
		
		//	�츮�� ���� ��� ����� ?�� ä�� �����͸� Map���� �˷��ֱ�� ����߱� ������...
//		HashMap map = new HashMap();
//		map.put("password", pw);
//		map.put("nick", id);
		
		//	�ڡڡ�
		//	�ſ� �߿�
		//		?�� ä�� �����͸� map���� �����ϴ� ��쿡��
		//		? ��� ����� #{�����Ѹ�}�� �����Ѹ��� Map�� Ű���� �Ǿ�� �Ѵ�.
		
		//	��� ������ ������ �ʴ´�.
		
		int	result = sqlSession.selectOne("login.login", map);
		return result;
		//	����
		//		select ���Ǹ� �����ϴ� �Լ�
		//		1.	select
		//			==>	�������� ���� ��� ���� �Լ�
		//		2.	selectList
		//			==>	�Ϲ������� ����� �������� ���� ����� ������ �� �ַ� ����Ѵ�.
		//				���� ��� �Խ��� ��� ���ϱ�......
		//		3.	selectMap
		//			==>	����� �����Ͱ� �������� ���
		//				���� ���	�Խ��� �󼼺��� ���ϱ�...
		//		4.	selectOne
		//			==>	����� �Ѱ��� ���
		//			==>	�Ϲ������� ����� ������ ���� ����� ������ �� �ַ� ����Ѵ�.

		//		���� ��츦 �����ؼ� ���� �Լ��� �����ϸ� �ȴ�.(��������� ���̴�.)
		
		//	����
		//		2�� �Ķ���� ���ʹ� ?�� ä�� �����͸� �˷��ִ� ����̴�.
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}






