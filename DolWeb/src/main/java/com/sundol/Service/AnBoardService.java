package com.sundol.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.DAO.AnBoardDAO;
import com.sundol.UTIL.PageUtil;
import com.sundol.VO.AnBoardVO;

public class AnBoardService {
	//	���� Ŭ������ �����Ͻ� ������ ����ϰ�
	//	Ȥ�� �����ͺ��̽� ó���� �ʿ��ϸ� DAO�� �̿��� ���̴�.
	//	�׷��Ƿ� DAO Ŭ������ �ʿ��� �����ε� �̰� ���� <bean> ó�� �� ���Ҵ�.
	@Autowired
	public 	AnBoardDAO		abDAO;
	
	//	���� �Խù� ��� ó���� ���� ���� �Լ�
	public void oriInsert(AnBoardVO		abVO) {
		//	���� VO �ȿ��� �����ͺ��̽� ó���� ���� ��� �����Ͱ� �غ�Ǿ� �ִ�.
		
		//	����
		//		�����ͺ��̽��� �������.
		abDAO.oriInsert(abVO);
	}
	
	//	�Խù� ��� ������ ó���� ���� ���� �Լ�
	public ArrayList getBoardList(int nowPage, PageUtil pInfo) {
		//	���� ��ɿ��� �� �������� �ʿ��� ���븸 ������ ���ǽ��� ����
		//	�����͸� �غ��ؾ� �Ѵ�.
		
		//	�츮�� �� �������� �ʿ��� �����͸� �������� ���� ����� ��������Ƿ�
		//	���� �������� ���� ��ȣ�� ���� ��ȣ�� �˷��־�� �Ѵ�.
		//	�̷����� ����(�� �������� 10���� ���̵��� ����ߴٸ�)
		//		���� ��ġ��		1�������̸�		1~
		//							2�������̸�		11~
		//							3�������̸�		21~
		int	start = (nowPage - 1) * (pInfo.listCount) + 1;
		//		���� ��ġ			���� ������ + 9
		//							1�������̸�		~10
		//							2�������̸�		~20
		int	end = start + (pInfo.listCount - 1);
		
		HashMap		map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = abDAO.getBoardList(map);
		
		return list;
		//	�ٵ� ������ ���� �����ϸ� �ȵȴ�.
		//	�ֳ��ϸ� PageInfo�� new ��Ű�� �ʾұ� �����̴�.
	}
	
	//	�� ������ ���� ���ϱ� ���� ��� ���� �Լ�
	public int getTotal() {
		//	����
		//		��� �̿��ؼ� ������ ���� ���ؼ� �˷��ش�.
		
		int		total = abDAO.getTotal();
		return total;
	}
	
	//	��ȸ�� ���� ���θ� �Ǵ��� �Լ�
	public boolean isHitNow(String id, int no) {
		//	��ȯ��
		//		��ȸ�� ������ �ؾ��ϸ� true�� ��ȯ�ϰ�
		//		��ȸ�� ������ ���� ���ƾ� �ϸ� false�� ��ȯ�� �����̴�.
		//	�Ű�����
		//		��ȸ�� ���� ���θ� �Ǵ��ϱ� ���ؼ��� ���� ������� ����������
		//		�˾ƾ� �Ѵ�.
		//	1.	�� ����� �̹� �� �� ��ȣ�� �˾Ƴ���.
		HashMap	map = abDAO.getHitNO(id);
		if(map == null || map.size() == 0) {
			//	�� ����� ������ �ѹ��� ���� ������ ���� ����̴�.
			//	��ſ�
			//		���� �� �۹�ȣ�� �ν�Ʈ �ؼ� �������� �� ���� ���� ��ȸ�� ������
			//		���ϵ��� �Ѵ�.
			HashMap temp = new HashMap();
			temp.put("user", id);
			temp.put("no", "#"+ no + "#");
			abDAO.updateHitNo("anBoard.insertHitNO", temp);
			
			//	������ �� �� �ֵ��� ���ش�.
			return true;
		}
		else {
			//	�� ����� �հ� ������ �ִ� ����̴�.
			//	�� ����� �̹� �� �� ��ȣ�� �˾Ƴ���.
			String	ano = (String) map.get("no");
			//	#10##25##101##7##52#
			//	���߿��� #5#�� �ִ����� Ȯ���ؾ� �ϹǷ�...
			int	test = ano.indexOf("#" + no + "#");
			if(test == -1) {
				//	�� ����� �̹����� ������ ���ٴ� ���̴�.
				//	��ſ� �̹��۵� �������� ������ ���ƾ� �Ѵ�.
				HashMap	temp = new HashMap();
				temp.put("user", id);
				temp.put("no", ano + "#" + no + "#");
				abDAO.updateHitNo("anBoard.updateHitNO", temp);
				//	��ȸ�� ������ �� �� �ֵ��� �Ѵ�.
				return true;
			}
			else {
				//	�� ����� �̹��۵� ������ �ִٴ� ���̴�.
				//	��ȸ�� ������ ���� ���ϵ��� �Ѵ�.
				return false;
			}
		}
	}
	//	���� ��ȸ�� ������ ó���� �Լ�
	//	(�� �Լ��� ���� �Լ��� ����� ���� ȣ�� ���ΰ� �Ǵܵ� �����̴�.)
	public void updateHit(int oriNo) {
		abDAO.updateHit(oriNo);
	}
	
	//	�󼼺��� ó���� ������ �Լ�
	public HashMap boardView(int oriNo) {
		//	�󼼺��⸦ ó���� ��쿡�� �츮��
		//	�󼼺��� ������ ������ �ϰ�
		AnBoardVO 	vo = abDAO.getBoardView(oriNo);
		//	��� ����� ������ �Ѵ�.
		//	==>		���� �׷��� �˾Ƴ���
		int		group = abDAO.getGroup(oriNo);
		//	==>		����� ������.
		ArrayList		list = abDAO.getAnList(group);
		
		HashMap	map = new HashMap();
		map.put("VIEW", vo);
		map.put("LIST", list);
		
		return map;
	}
	
	//	������ ������ �˾Ƴ��� ���� �Լ�
	public AnBoardVO getOriInfo(int oriNo) {
		AnBoardVO 	vo = abDAO.getBoardView(oriNo);
		return vo;
	}
	
	//	��� �Է� ó���� ���� �Լ�
	public void anIsert(AnBoardVO vo) {
		
		//	���� �������� ������(1588)
		HashMap	map = new HashMap();
		map.put("bgroup", vo.bgroup);
		map.put("border", vo.border);
		
		abDAO.updateOrder(map);
		
		//	����� �Է��ϰ�
		abDAO.insertAn(vo);

	}
	
	//	�˻� ó�� ���� �Լ�
	public ArrayList	boardSearch(AnBoardVO aVO) {
		//	�˻��Ҷ�� vo �߿��� kind, word�� Map���� ����� �ֱ�� �����Ƿ�
		HashMap	map = new HashMap();
		map.put("kind", aVO.kind);
		map.put("word", aVO.word);
		
		ArrayList	list = abDAO.boardSearch(map);
		return list;
	}
	
	//	������ ���� ���� ���� ������ ó�� �Լ�
	public AnBoardVO getModifyView(int oriNo) {
		AnBoardVO 	vo = abDAO.getBoardView(oriNo);
		return vo;
	}

	//	���� ó���� ���� �Լ�
	public int updateBoard(AnBoardVO aVO) {
		return abDAO.updateBoard(aVO);
	}
	
	//	���� ó���� ���� �Լ�
	public int deleteBoard(AnBoardVO aVO) {
		return abDAO.deleteBoard(aVO);
	}
}










