package com.sundol.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.sundol.DAO.ShopManagerDAO;
import com.sundol.VO.ShopManagerVO;

public class ShopManagerService {
	//	이 클래스는 보나마다 DAO를 사용해서 비지니스 로직을 처리할 예정이다.
	//	이미 등록해 놓은 DAO를 자동 주입 방식으로 사용하도록 조치하자.
	
	@Autowired
	private	ShopManagerDAO		smDAO;
	//	노파심
	//		@Autowired는 클래스 이름을 가지고 자동 주입하게 된다.
	//		그러므로 변수 이름은 아무관계가 없다.
	
	//	이 서비스 클래스 역시 컨트롤러에서 사용할 예정이므로
	//	그곳에서도 자동 주입이 가능하도록 <bean> 처리 해 놓자.
	
	/*
	 * 	대카테고리 검색 처리 함수
	 */
	public ArrayList getLCategory() {
		return smDAO.getLCategory();
	}
	
	/*
	 * 	대카테고리 등록 처리 함수
	 */
	public void insertLCategory(String lname) {
		smDAO.insertLCategory(lname);
	}
	
	/*
	 * 	중카테고리 등록 처리 함수
	 */
	public void insertMCategory(ShopManagerVO vo) {
		//	vo 내용중에서 질의에 필요한 데이터만 Map으로 묶자
		
		HashMap map = new HashMap();
		map.put("lcate", vo.getLcode());
		map.put("mname", vo.getMname());
		
		smDAO.insertMCategory(map);
	}
	
	/*
	 * 	소카테고리 등록 처리 함수
	 */
	public void insertSCategory(ShopManagerVO vo) {
		//	vo 내용중에서 질의에 필요한 데이터만 Map으로 묶자
		
		HashMap map = new HashMap();
		map.put("mcate", vo.getLcode());
		map.put("sname", vo.getSname());
		
		smDAO.insertSCategory(map);
	}

	/*
	 * 	중카테고리 검색 처리 함수
	 */
	public ArrayList selectMCate(String lcode) {
		return smDAO.selectMCategory(lcode);
	}
}










