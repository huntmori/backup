package com.sundol.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.DAO.FileBoardDAO;
import com.sundol.UTIL.PageUtil;
import com.sundol.VO.FileBoardVO;

public class FileBoardService {
	//	�������� �� ���񽺴� �ַ� DAO�� �̿��ؼ� DB ó�� �� �����̴�.
	@Autowired
	private	FileBoardDAO		fDAO;
	
	/*
	 * 	�Խù� ��� ó�� �Լ�
	 */
	public void insertBoard(FileBoardVO fVO, ArrayList list) {
		//	�Ϲ� �Խù� ���
		fDAO.insertBoard(fVO);
		
		//	÷������ ����� ����.
		//	list�� ��ϵ� ÷�� ������ ������ �� ������ �ִ�.
		int	size = list.size();		//	÷�� ������ ����
		for(int i = 0; i < size; i++) {
			HashMap map = (HashMap) list.get(i);
			//	������ �� �ȿ� ������ ����� ��� ������ ������ �־�� �Ѵ�.
			//	�ٵ� �츮�� �� �ȿ� ������ ��ȣ�� ����.
			map.put("oriNo", fVO.getNo());
			fDAO.insertFileInfo(map);
		}
	}
	
	/*
	 * 	�� ������ ���� ó�� �Լ�
	 * 	==>		�� ������ ������ ���ϴ� ������ ������ ������ �˱� �����̴�.
	 * 			�ƿ� ���⼭ ������ �������� ���� ��������.
	 * 
	 * 			������ ������ ���ϱ� ���ؼ��� ���� ������, �� ������ ������ �ʿ��ϹǷ�
	 * 			���߿��� ���� �˰��ִ� nowPage�� �˷��ֵ��� �Ѵ�.
	 */
	public PageUtil getPageInfo(int nowPage) {
		int	total = fDAO.getTotal();
		
		PageUtil pInfo = new PageUtil(nowPage, total);
		return pInfo;
	}
	
	/*
	 * 	��� ���ϱ� ó�� �Լ�
	 * ==>		�츮�� ���� ���������� �ʿ��� ��ϸ� ������� �����Ƿ�
	 * 			��� ���ϱ⸦ ���ؼ��� ������ ������ �ʿ��ϴ�.
	 */
	public ArrayList	getBoardList(PageUtil pInfo) {
		//	���ϰ����ϴ� ����� ���� ��ġ�� ������ ��ġ�� �˾Ƴ���.
		int		start = (pInfo.nowPage - 1) * pInfo.listCount + 1;
		int		end = pInfo.nowPage * pInfo.listCount;
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = fDAO.getBoardList(map);
		return list;
	}
	
	/*
	 * 	�󼼺��� ���� ������ �˻��� ���� ���� �Լ�
	 */
	public HashMap getBoardView(int oriNo, String kind, HashMap map1) {
		FileBoardVO	vo = fDAO.getBoardView(oriNo);
		
		ArrayList		list = fDAO.getUploadFile(oriNo);
		
		HashMap		map = fDAO.getPreNext(oriNo, kind, map1);
		
		HashMap		rMap = new HashMap();
		rMap.put("VIEW", vo);
		rMap.put("FILE", list);
		rMap.put("PRENEXT", map);
		
		return rMap;
	}
	
	/*
	 * 	�ٿ�ε� ���� ���� ó���� ���� �Լ�
	 */
	public FileBoardVO getDownloadInfo(int oriNo) {
		return fDAO.getDownloadInfo(oriNo);
	}
	/*
	 * 	���� ���� Ŭ������ ���� �������̽��� �����
	 * 	�� �������̽��� ��� �޾Ƽ� ���鵵�� �����ϰ� �ִ�.
	 * 	(��� ���� Ŭ������ ������ ������ �ϵ��� �ϰ� �ִ�.)
	 * 
	 * 	������ 3�̻� ���� ���� ������ ������ ���� �ʾƵ� ���������� ó���� ���ְ� �ȴ�.
	 */
	
	/*
	 * 	�˻� ���� ���ϱ� ó�� �Լ�
	 */
	public PageUtil getSearchTotal(FileBoardVO fVO) {
		//	�˻� ���� ���ϱ�� target, word�� �˾ƾ� �Ѵ�.
		
		//	�˻� ������ HashMap���� �Ķ���͸� �˷��ֱ�� �����Ƿ�....
		HashMap	map = new HashMap();
		map.put("target", fVO.getTarget());
		map.put("word", fVO.getWord());
		
		int		total = fDAO.getSearchTotal(map);
		
		PageUtil	pInfo = new PageUtil(fVO.getNowPage(), total);
		
		return pInfo;
	}
	/*
	 * 	�˻� ó�� �Լ�
	 */
	public ArrayList	getSearch(FileBoardVO fVO, PageUtil pInfo) {
		//	�˻��� �ϱ� ���ؼ��� target, word, start, end�� �ʿ��ϴ�.
		//	start, end�� ���� �������� �̿��ؼ� ����� �־�� �Ѵ�.
		
		int		start = (pInfo.nowPage - 1) * pInfo.listCount + 1;
		int		end = pInfo.nowPage * pInfo.listCount;
		
		//	�� ������ VO�� �̿��ؼ� ���Ǹ� �ϱ�� ����ߴ�.
		fVO.setStart(start);
		fVO.setEnd(end);

		ArrayList	list = fDAO.getSearch(fVO);
		return list;
	}
}
