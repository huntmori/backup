package com.sundol.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.VO.AnBoardVO;

public class AnBoardDAO extends SqlSessionDaoSupport {
	//	�� Ŭ������ ���� ����� �����ϱ� ���� Ŭ�����̴�.
	//	�� Ŭ�������� ���� �߿��� ���� ���ؼ� Ǯ�� �ִ� ���ؼ��� �̿��ϴ� ���̴�.
	//	����
	//		myBatis���� ���ؼ��� "����"�̶�� �θ���.
	//	������ �����ϴ� ������ ������ �ϴµ�... �츮�� �̹� myBatis ����� �Ҷ�
	//	���� ������ ���� SqlSessionTemplate�� <bean> ó���� �������Ƿ�
	//	DI ������� �ҷ����� �� ���̴�.
	@Autowired
	public SqlSessionTemplate		sSession;
	
	//	�ʿ��� ���� ����� ������ �Լ��� ������.
	
	//		���� �Խù� ����� ���� ���� ��� ���� �Լ�
	public void oriInsert(AnBoardVO abVO) {
		sSession.insert("anBoard.oriInsert", abVO);
		//	��� ���� �����Լ��� ù��° �Ķ���ʹ� ���� ���� ����� ã�ƿ�
		//	���� �ڵ尪�� �Է��Ѵ�.
		//		���� �ڵ尪		SQL������ namespace.������ id
		
		//	�ι�° �Ķ���� ���ʹ� ���� ���࿡ �ʿ��� �����͸� �Է��ϴ� �κ��̴�.
		
		
		//	��¥ ����ִ°���
		//	myBatis�� ����ϸ� ������Ʈ��Ʈ�� �ڵ� �����ǰ�
		//							����� close�� �ڵ� ó���ȴ�.
	}
	
	//	�Խù� ��� ������ ���� ���� ���
	public ArrayList getBoardList(HashMap map) {
		//	myBatis�� SQL���� resultType�� ���ٸ� ����ؼ� ������ ������...
		//	DAO�� ������ ������ ����� �ޱ� ������ �������� ����ؼ� �޾ƾ� �Ѵ�.
		//	���� �������� ���� ������ �ִٸ� ArrayList�� �޾ƾ� �Ѵ�.
		
		//	()�ȿ��� �� ���� ����� ������ �� �ʿ��� �����͸� �����ϴ� ���̹Ƿ�
		//	�츮�� �����͸� Map���� �˷��ֱ�� �����Ƿ�....
		
		ArrayList	list = (ArrayList) sSession.selectList("anBoard.boardList", map);
		return list;
	}
	
	//	�� ������ ���� ���ϱ� ���� ��� ���� �Լ�
	public int getTotal() {
		return sSession.selectOne("anBoard.getTotal");
		
		//	����	DAO�� �ݵ�� Ư�� ���Ǹ� ���� �Լ��� ���� �����ؾ� �ϴ� ���� �ƴϴ�.
		//			�Ѱ��� �Լ��� �̿��ؼ� �ٸ� �������� ���Ǹ� ������ ���� �ִ�.
		
		/*	��>		public void ????(String code) {
		 * 				sSesseion.?????(code);
		 * 			}
		 * 
		 * 			�����
		 * 			sDao.?????("anBoard.getTotal")		<==		�� ������ ���� ���ϱ�
		 * 			sDao.?????("anBoard.getTotal1")	<==		�ٸ� ���� ����� ���� �� �� �ִ�.
		 * 		�� ����� �̿��ؼ� ������ ���� �ڵ带 �ٸ��� �˷��ָ�
		 * 		�׶� �׶����� �ٸ� ���� ����� �����ϵ��� �� ���� �ִ�.
		*/	
	}
	
	//	�̹� �� �� ��ȣ�� ��ȸ�� ���� ����� ������ �Լ�
	public HashMap getHitNO(String user) {
		return sSession.selectOne("anBoard.getHitNO", user);
		//	����
		//		1.	�Ķ���Ͱ� Map�̸� #{����}�� Map�� Ű���� �Ǿ�� �Ѵ�.
		//		2.	�Ķ���Ͱ� VO�̸� #{����}�� setXxx �Լ��� �̸��� �Ǿ�� �Ѵ�.
		//		3.	�Ķ���Ͱ� �Ϲ� �������̸� #{����}�� �������� �����̸��� �Ǿ�� �Ѵ�.
	}
	
	//	���� ���ݺ��� �ϳ��� �Լ��� �̿��ؼ� �ΰ��� ���� ����� �����ϵ��� 
	//	���� �����̴�.
	
	public void updateHitNo(String code, HashMap map) {
		if(code.equals("anBoard.insertHitNO")) {
			sSession.insert(code, map);
		}
		else {
			sSession.update(code, map);
		}
		
		//	���� �ʿ��� �� �Լ��� ������ ��
		//	sService.updateHitNo("anBoard.isertHitNO", map)	�� �ϸ�
		//		insert ���� ����� ����� ���̰�
		//	sService.updateHitNo("anBoard.updateHitNO", map)	�� �ϸ�
		//		update ���� ����� ����� ���̴�.
	}
	//	���� ��ȸ���� ������ �Լ�
	public void updateHit(int oriNo) {
		sSession.update("anBoard.updateHit", oriNo);
	}
	
	//	�󼼺��� ���Ǹ� ������ �Լ�
	public AnBoardVO getBoardView(int oriNo) {
		//	�Ķ���Ͱ� �Ϲ� �������̸� #{Ű��}�� ����� ������ �̸��� �����ؾ��Ѵ�.
		//	�ڡڡ�
		//	���� ����� resultType�� ���� ���� ����� ���ٸ� ������ �����ض�
		//	DAO�� ��ȯ���� ���� ���� �� �ִ� ��츦 ����ؼ� ó���ؾ� �Ѵ�.
		return sSession.selectOne("anBoard.boardView", oriNo);
	}
	//	�� �ۿ� ���� �׷��� �˾Ƴ��� ���� ���� ��� ���� �Լ�
	public int getGroup(int oriNo) {
		return (Integer) sSession.selectOne("anBoard.getGroup", oriNo);
	}
	
	//	����� ���� ���Ǹ� ������ �Լ�
	public ArrayList getAnList(int bgroup) {
		return (ArrayList) sSession.selectList("anBoard.anList", bgroup);
	}
	//	��� �Է� ���� ���� �Լ�
	public void insertAn(AnBoardVO vo) {
		sSession.insert("anBoard.anInsert", vo);
	}
	
	//	��� �Է½� ���� ���� ���� �Լ�
	public void updateOrder(HashMap map) {
		sSession.update("anBoard.orderSet", map);
	}
	
	//	�˻� ���� ���� �Լ�
	public ArrayList boardSearch(HashMap map) {
		return (ArrayList) sSession.selectList("anBoard.search", map);
	}
	
	//	���� ���� ���� �Լ�
	public int updateBoard(AnBoardVO abVO) {
		//	update, delete�� �����ϸ� �� ��ɿ� ���ؼ� ����� �������� ������
		//	�� �� �ִ�.
		//	�� ������ 0�̸� ������� �������̰�
		//	�� ������ 1�̸� ����� ���̴�.
		return sSession.update("anBoard.updateBoard", abVO);
	}
	
	//	���� ���� �����Լ�
	public int deleteBoard(AnBoardVO abVO) {
		return sSession.update("anBoard.delete", abVO);
	}
}









