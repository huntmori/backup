package com.sundol.VO;

import java.sql.Date;
import java.util.StringTokenizer;

/**
 * 	�亯�� �Խ��� ó���� ���� VO Ŭ�����̴�.
 * 
 * 	@author 	���ȯ
 *	@date		2017.10.19
 */
public class AnBoardVO {
	public String	title;
	public	String	body;
	public	String	password;
	public	String	tags;
	public	int		no;
	public String	writer;
	public	Date	wday;
	public	int		hit;
	public	int		rno;
	//	��ȸ�� ������ ���� �Ķ���� �غ�
	public	int		nowPage;
	public	int		oriNo;
	//	SELECT ���Ǹ� �̿��� ����� VO�� �ޱ� ���ؼ���
	//	���� �����͸� ����� setXxx()�� �غ�Ǿ�� �Ѵ�.
	public	int		bgroup;
	public	int		bstep;
	public	int		border;
	//	VO�� �غ��� ������
	//		�Ķ���ͷ� ���޵� �����͸� ����� ����(setXxx())
	//		SELECT ���� ����� ����� ����� ����(setXxx())
	public	String		kind;
	public	String		word;
	//	�� �Լ��� Vo�� �����͸� �信�� ������ ���� ���ؼ� �ʿ��� ���̴�.
	public String getTitle() {
		return title;
	}
	//	�� �Լ��� �信�� Vo���� �����͸� �ֱ� ���ؼ� �ʿ��� ���̴�.
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		//	�� �Լ��� �信�� ����ڰ� �Է��� �����͸� �˷��ִ� �Լ��̴�.
		//	tags��� ������ ����ڰ� �Է��� �����͸� �˷��ָ�	
		//	����ڰ� �Է��� ������ �� tags ������ ����� �����ʹ�
		//		�ڹ� ������, ��		�� �������� �ԷµǾ� ���� ���̴�.
		//	�츮�� #�ڹ�#������#��		�� �������� ����� ���ƾ� �Ѵ�.
		
		StringTokenizer	token = new StringTokenizer(tags, " ,");
		//		�ڹ� ������, ��		==>		�ڹ� 		������		��
		String	realData = "";
		while(token.hasMoreTokens()) {
			String	data = token.nextToken();
			realData = realData + "#" + data;
		}
		
		//	��¥ �߿��� ��Ȱ��	
		//	����ڰ� ������ �����Ͱ� ���� ����� ��������
		//	���°� �ٸ��� setXxx �Լ����� �����ؼ� ����� �� �ִ�.
		
		
		
		this.tags = realData;
		//	�ڽ��� ������ �ִ� ���� ������ �� �����͸� �״�� �Է��� ���´�.
	}
	//	SQL ���Ͽ��� �����͸� ?�� ä��� ���� �Լ�
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getWday() {
		return wday;
	}
	public void setWday(Date wday) {
		this.wday = wday;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getOriNo() {
		return oriNo;
	}
	public void setOriNo(int oriNo) {
		this.oriNo = oriNo;
	}
	public int getBgroup() {
		return bgroup;
	}
	public void setBgroup(int bgroup) {
		this.bgroup = bgroup;
	}
	public int getBstep() {
		return bstep;
	}
	public void setBstep(int bstep) {
		this.bstep = bstep;
	}
	public int getBorder() {
		return border;
	}
	public void setBorder(int border) {
		this.border = border;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	
}
