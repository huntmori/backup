package com.sundol.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import 	org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.VO.FileBoardVO;

public class FileBoardDAO extends SqlSessionDaoSupport {

	@Autowired
	private SqlSessionTemplate		sSession;
	//	�� DAO�� �������� ���񽺿��� ����� �����̴�.
	//	��� ���񽺿��� �� Ŭ������ new ���Ѽ� ����ϸ� �Ǵµ�....
	
	//	���񽺿����� �� DAO�� �̸� ����� ���� ����ϵ��� ��ġ�� ����.
	//	xml ������ �� Ŭ������ <bean> ó�� �س����� �ȴ�.
	
	/*
	 * 	�Խù� ��� ���� ���� �Լ�
	 */
	public void insertBoard(FileBoardVO fVO) {
		sSession.insert("fileBoard.boardInsert", fVO);
	}
	
	/*
	 * ���� ���� ��� ���� ���� �Լ�
	 */
	public void insertFileInfo(HashMap map) {
		sSession.insert("fileBoard.fileInfoInsert", map);
	}
	
	/*
	 * 	�� ������ ���� ���ϱ� ���� ���� �Լ�
	 */
	public int getTotal() {
		return (Integer) sSession.selectOne("fileBoard.getTotal");
	}
	
	/*
	 * 	�Խù� ��� ���ϱ� ���� ���� �Լ�
	 */
	public ArrayList getBoardList(HashMap map) {
		return (ArrayList) sSession.selectList("fileBoard.getBoardList", map);
	}
	/*
	 * 	�Խù� �󼼺��� ���� ���� �Լ�
	 */
	public FileBoardVO	getBoardView(int oriNo) {
		return (FileBoardVO) sSession.selectOne("fileBoard.boardView", oriNo);
	}
	/*
	 * �Խù� ÷������ �˻� ���� ���� �Լ�
	 */
	public ArrayList	getUploadFile(int oriNo) {
		return (ArrayList) sSession.selectList("fileBoard.boardFile", oriNo);
	}
	/*
	 * ������ ������ ���� �����Լ�
	 */
	public HashMap getPreNext(int oriNo, String kind, HashMap map) {
		//	�� �Լ� �ϳ������� 2���� ���� ����� ������ �����̴�.
		//		kind	�� list, search�� ������ ����ؼ�
		//	�̰��� �̿��ؼ� �� �� �� ���Ǹ� �����ϵ��� �Ѵ�.
		if(kind.equals("list")) {
			return (HashMap) sSession.selectOne("fileBoard.prenext", oriNo);
		}
		else {
			return (HashMap) sSession.selectOne("fileBoard.searchprenext", map);
		}
	}
	
	/*
	 * 	�ٿ�ε� ������ ���� �˻� ���� ���� �Լ�
	 */
	public FileBoardVO	getDownloadInfo(int oriNo) {
		return (FileBoardVO) sSession.selectOne("fileBoard.downloadInfo", oriNo);
	}
	
	/*
	 * 	�˻� ��� ���� ���ϱ� ���� ���� �Լ�
	 */
	public int getSearchTotal(HashMap map) {
		return (Integer) sSession.selectOne("fileBoard.searchTotal", map);
	}
	/*
	 * 	�˻� ���� ���� �Լ�
	 */
	public ArrayList	getSearch(FileBoardVO fVO) {
		return (ArrayList) sSession.selectList("fileBoard.searchList", fVO);
	}
	
	
}




