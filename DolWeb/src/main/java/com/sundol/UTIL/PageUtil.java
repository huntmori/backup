package com.sundol.UTIL;
/* �� Ŭ������  ������ �̵� ����� �����ϱ� ���ؼ� �ʿ��� 7���� ������ ����ϰ� ������� Ŭ�����̴�.
 * ����¡ ó���ϱ� ***************************************************

[����]	[1][2][3][4][5]		[����]
[����]	[6][7][8][9][10]	[����]
���� ����� ����� ���ؼ� �ݵ�� �˾ƾ� �� ����

1. ���� ������
2. �� ������ ����
	=> �� 2���� �ݵ�� �������� �˷���� �� ����

3. �� ȭ�鿡 ������ ����� ����  - 10��
4. �� ȭ�鿡�� �̵� ������ ������ ��
	=> �����ڰ� ������ �����ؾ� �� ����

5. �� ������ �� 
6. ȭ�鿡 ǥ���� �������� ���� ������
7. ȭ�鿡 ǥ���� �������� ������ ������
	=> ��꿡 ���ؼ� ������� ����
 */
public class PageUtil {
	public int nowPage;		// ���� �������� ����� ����
	public int totalCount;	// �� ������ ������ ����� ����
	
	public int listCount;	// �� ȭ�鿡 ������ ����� ���� - 10��
	public int pageGroup;	// �� ȭ�鿡 ������ ������ �� - 5������
	
	public int totalPage;	// �� ������ ��
	public int startPage;	// ���� ������
	public int endPage;		// �� ������
	
	// �������� �� Ŭ������ new ��Ű�鼭 �ʼ� ������ �˷��ֱ�� ����.
	public PageUtil(int nowPage, int totalCount) {
		this(nowPage, totalCount, 3);
	}
	//	������ �Լ��� �����ε� �Ͽ� �ٸ� ����� �ʿ��ϸ� �޵��� ����.
	public PageUtil(int nowPage, int totalCount, int listCount) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		
		// �̵��� �� �κ��� �����ؼ� ����� Ȯ���ϵ��� ����.
		this.listCount = listCount;
		pageGroup = 3;
		
		// �Ʒ��ʿ� ���� �Լ��� �ϳ��� ȣ���ؼ� ������ 3���� ������ ��������.
		calcTotalPage();
		calcStartPage();
		calcEndPage();
	}

	/*
	 * 	����
	 * 		PageUtil pInfo = new PageUtil(nowPage, total);	//	������ ���������� 3����
	 * 
	 * 		PageUtil pInfo = new PageUtil(nowPage, total, ����);	//	���ϴ� ������� ����Ѵ�.
	 */

	// �� ���������� ����� �Լ�
	private void calcTotalPage() {
		// �� ȭ�鿡 10���� ����ϱ�� �����Ƿ� �� ������ 100���̸� 10�������� �ʿ��ϰ� 
		// �� ������ 101���̸� 11�������� �ʿ��ϴ�.
		totalPage = (totalCount % listCount) == 0 ? (totalCount / listCount) : (totalCount / listCount) + 1;
	}
	
	// ���� �������� ����� �Լ�
	private void calcStartPage() {
		// ����
		// ���� �������� �� ��° �׷������� �˾Ƴ���,
		int tempGroup = (nowPage - 1) / pageGroup + 1;
		// �� �׷��� ���� �������� ���Ѵ�.
		startPage = (tempGroup - 1) * pageGroup + 1;
	}
	
	// ������ �������� ����� �Լ�
	private void calcEndPage() {
		// ���� ������ + pageGroup - 1�� �ϸ� �ȴ�.
		endPage = startPage + pageGroup - 1;
		// �ٸ� ������ �������� �� ����� �ƴ� �� �ִ�.
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	
	// �� ������ view���� JSTL�� �̿��ؼ� ����ؾ� �ϹǷ� 
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getPageGroup() {
		return pageGroup;
	}
	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}	
}