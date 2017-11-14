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
 * 	���θ� �����ڿ� ��Ʈ�ѷ�
 */
@Controller
public class ShopManagerController {
	@Autowired
	private	ShopManagerService		smS;
	
	/*
	 * 	ī�װ� ��� �� ��û ó�� �Լ�
	 */
	@RequestMapping("/Shop/CategoryReg")
	public ModelAndView categoryReg() {
		//	ī�׷θ� ��� �������� �ϴ� ��ī�װ��� �����־�� �Ѵ�.
		//	���߿� Ajax�� �̿��ؼ� ��ī�װ��� �����ϸ� �ش� ��ī�װ��� 
		//	��ī�װ��� ���߿� �����־�� �Ѵ�.
		
		//	��ī�װ� �˻�
		ArrayList	list = smS.getLCategory();
		//	�並 �θ���.
		ModelAndView		mv = new ModelAndView();
		mv.addObject("LLIST", list);
		//	��Ʈ�ѷ��� ������ ���� ������ ���� �ʿ䰡 ����.
		//	��Ʈ�ѷ�		/Shop/CategoryReg.sun
		//	��				/Shop/Manager/CategoryReg.jsp		�� �����ϴ�.
		mv.setViewName("Shop/Manager/CategoryReg");
		return mv;
	}
	
	/*
	 * 	��ī�װ� ��� ó�� �Լ�
	 */
	@RequestMapping("/Shop/LCategoryReg")
	public ModelAndView	lCategoryReg(@RequestParam("lname") String lname) {
		//	1.	�Ķ���� �ް�
		//	2.	��� ����ϰ�
		smS.insertLCategory(lname);
		//	3.	�並 �θ���.
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/CategoryReg.sun");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	��ī�װ� ��� ��û ó�� �Լ�
	 */
	@RequestMapping("/Shop/MCategoryReg")
	public ModelAndView	mCategoryReg(ShopManagerVO vo) {
		
		smS.insertMCategory(vo);

		//	3.	�並 �θ���.
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/CategoryReg.sun");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	��ī�װ� �˻��� ���� ��û ó�� �Լ�
	 */
	@RequestMapping("/Shop/MCategorySearch")
	@ResponseBody
	public HashMap mCategorySearch(@RequestParam("lcode") String lcode) {
		//	����
		//		�Ķ���� �ް�
		//		��񿡼� ��ī�װ� ������
		ArrayList	list = smS.selectMCate(lcode);
		
		//		JSON ������� ���� ������ ���� �����Ѵ�.
		//	����
		//		@ResponseBody�� ����ϸ� �� ��Ʈ�ѷ��� ������ �����
		//		��û�� ���� �κ����� ������ �ϰ� �ȴ�.
		//		(�츮�� JQeury���� ��û�����Ƿ� JQuery�� �����ϰ� �ȴ�.)
		//		�̶� ���� ����� JSON �������� ���� �����ϰ� �ȴ�.
		
		//	�츮�� ���� JSON ������ ���
		//		{'mcate' : [ 	{'code' : '00010001', 'name' : '�繫��ǰ'},
		//						{'code' : '00010001', 'name' : '�繫��ǰ'},
		//						{'code' : '00010001', 'name' : '�繫��ǰ'} ] }
		//	�����̵� ��񿡼� ������ �� list���� vo�� �迭 ���·� �����Ǿ� �ִ�. 
		
		HashMap	map = new HashMap();
		map.put("mcate", list);
		
		return map;
	}
	
	/*
	 *	��ī�װ� ��� �Լ�
	 */
	@RequestMapping("/Shop/SCategoryReg")
	public ModelAndView sCategoryReg(ShopManagerVO vo) {
		//	�����ͺ��̽��� ����Ѵ�.
		smS.insertSCategory(vo);
		//	3.	�並 �θ���.
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Shop/CategoryReg.sun");
		mv.setView(rv);
		return mv;
	}
}













