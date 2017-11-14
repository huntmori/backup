package com.sundol.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sundol.Service.ShopManagerService;
import com.sundol.VO.ShopManagerVO;

/*
 * 	쇼핑몰 관리자용 컨트롤러
 */
@Controller
public class ShopManagerController {
	@Autowired
	private	ShopManagerService		smS;
	
	/*
	 * 	카테고리 등록 폼 요청 처리 함수
	 */
	@RequestMapping("/Shop/CategoryReg")
	public ModelAndView categoryReg() {
		//	카테로리 등록 폼에서는 일단 대카테고리는 보여주어야 한다.
		//	나중에 Ajax를 이용해서 대카테고리를 선택하면 해당 대카테고리의 
		//	중카테고리를 나중에 보여주어야 한다.
		
		//	대카테고리 검색
		ArrayList	list = smS.getLCategory();
		//	뷰를 부른다.
		ModelAndView		mv = new ModelAndView();
		mv.addObject("LLIST", list);
		//	컨트롤러의 계층과 뷰의 계층은 같을 필요가 없다.
		//	컨트롤러		/Shop/CategoryReg.sun
		//	뷰				/Shop/Manager/CategoryReg.jsp		이 가능하다.
		mv.setViewName("Shop/Manager/CategoryReg");
		return mv;
	}
	
	/*
	 * 	대카테고리 등록 처리 함수
	 */
	@RequestMapping("/Shop/LCategoryReg")
	public ModelAndView	lCategoryReg(@RequestParam("lname") String lname) {
		//	1.	파라메터 받고
		//	2.	디비에 등록하고
		smS.insertLCategory(lname);
		//	3.	뷰를 부른다.
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/CategoryReg.sun");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	중카테고리 등록 요청 처리 함수
	 */
	@RequestMapping("/Shop/MCategoryReg")
	public ModelAndView	mCategoryReg(ShopManagerVO vo) {
		
		smS.insertMCategory(vo);

		//	3.	뷰를 부른다.
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/CategoryReg.sun");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	중카테고리 검색을 위한 요청 처리 함수
	 */
	@RequestMapping("/Shop/MCategorySearch")
	@ResponseBody
	public HashMap mCategorySearch(@RequestParam("lcode") String lcode) {
		//	할일
		//		파라메터 받고
		//		디비에서 중카테고리 꺼내서
		ArrayList	list = smS.selectMCate(lcode);
		
		//		JSON 방식으로 응답 문서를 만들어서 제공한다.
		//	참고
		//		@ResponseBody를 사용하면 이 컨트롤러가 생산한 결과는
		//		요청한 본문 부분으로 응답을 하게 된다.
		//		(우리는 JQeury에서 요청했으므로 JQuery로 응답하게 된다.)
		//		이때 응답 결과는 JSON 형식으로 만들어서 응답하게 된다.
		
		//	우리가 만들 JSON 문서의 모양
		//		{'mcate' : [ 	{'code' : '00010001', 'name' : '사무용품'},
		//						{'code' : '00010001', 'name' : '사무용품'},
		//						{'code' : '00010001', 'name' : '사무용품'} ] }
		//	다행이도 디비에서 가지고 온 list에는 vo가 배열 형태로 나열되어 있다. 
		
		HashMap	map = new HashMap();
		map.put("mcate", list);
		
		return map;
	}
	
	/*
	 *	소카테고리 등록 함수
	 */
	@RequestMapping("/Shop/SCategoryReg")
	public ModelAndView sCategoryReg(ShopManagerVO vo) {
		//	데이터베이스에 등록한다.
		smS.insertSCategory(vo);
		//	3.	뷰를 부른다.
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/CategoryReg.sun");
		mv.setView(rv);
		return mv;
	}
}













