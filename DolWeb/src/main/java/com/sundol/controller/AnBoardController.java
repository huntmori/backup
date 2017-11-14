package com.sundol.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sundol.Service.AnBoardService;
import com.sundol.UTIL.PageUtil;
import com.sundol.VO.AnBoardVO;

@Controller
@RequestMapping("/AnBoard")
public class AnBoardController {
	//	�տ����� ���� �������� ��Ʈ�ѷ��� �ַ� ���� Ŭ������ �̿��ؼ�
	//	�۾��� ������ �����̴�.
	
	@Autowired
	public	AnBoardService		abService;
	
	//	��� ���� ��û ó�� �Լ�
	@RequestMapping("/BoardList")
	public ModelAndView boardList
		(@RequestParam(value="nowPage", defaultValue="1") int nowPage) {
		/*
		String strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(strPage == null || strPage.length() == 0) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		*/
		
		//	����
		//	�Ķ���� �ް�
		//	�����ͺ��̽����� ��� �̾ƿ���
		//		1.	�� ������ ������ ���ؼ� ������ ������ �����
		int	total = abService.getTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total);
		
		//		2.	������ ������ �̿��ؼ� �� �������� �ʿ��� ��ϸ� ���ؼ�
		ArrayList	list = abService.getBoardList(nowPage, pInfo);
		//	�信 �𵨷� �˷��ְ�
		
		
		//	�並 �θ���.
		ModelAndView	mv = new ModelAndView();
		//	�� �Է�.......		�Լ�		addObject("Ű��", ������);
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("AnBoard/BoardList");
		return mv;
	}
	
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm() {
		//	����
		//		�ʿ��ϴٸ�... ���� �˻縦 �ؼ� ������ ������ �۾��⸦ ���ϵ��� ó���ϰ�...
		//		(�� ������ ������� �Խ����� �ϼ��Ǹ�... �߰��� ����)
		//		������ �׽�Ʈ �Ҷ��� �ݵ�� �α��� �ϰ� �׽�Ʈ �ϼ���....
		
		//		�並 ȣ���Ѵ�.
		ModelAndView	mv = new ModelAndView();
		mv.setViewName("AnBoard/WriteForm");
		return mv;
	}
	
	@RequestMapping("/WriteProc")
	public ModelAndView writeProc(AnBoardVO aVo, HttpSession session){
		//	����
		//		�Ķ���� �ް�
		//		==>		���� �Ķ���ʹ� title, body, password, tags�� �޾Ҵ�.
		//				������ �����ͺ��̽����� ���� 4���� �ܿ�
		//				�۾��̵� ���� ����ؾ� �Ѵ�.
		
		//		�۾��̴� ���ǿ��� �α����� ����� id�� ���ؼ� ó���ϵ��� ����ߴ�.
		//		����	VO Ŭ���� �ȿ� �۾��� ������ �˷��־�� �Ѵ�.
		//				�� �۾��� ��� �ؾ��ϴ°�?
		//				���	��Ʈ�ѷ�, ���� �� �ƹ��������� �ϸ� �ȴ�.
		
		//		�츮�� ��Ʈ�ѷ����� �ϱ�� ����.
		String	id = (String) session.getAttribute("UID");
		aVo.writer = id;
		
		//		��� �Է��ϰ�
		abService.oriInsert(aVo);
		//		�並 �θ���.
		RedirectView	rv = new RedirectView("../AnBoard/BoardList.sun");
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		return mv;
	}
	
	//	��ȸ�� ���� ��û�� ó���� �Լ�
	@RequestMapping("/HitProc")
	public ModelAndView hitProc(AnBoardVO aVO, HttpSession session) {
		//	����
		//		�Ķ���� �ް�
		//		��ȸ�� ó���ϰ�
		//		��ȸ�� ó�� ���θ� �Ǵ��Ѵ�.
		//	��ȸ�� �Ǵ��� �ϱ� ���ؼ��� ���� �� �������� ���ǿ��� �˾ƾ� �Ѵ�.
		String id = (String) session.getAttribute("UID");
		//	���� ������� �� �������� �˷��ָ� ��ȸ�� ���θ� �Ǵ� �� �ش�.
		boolean	isHit = abService.isHitNow(id, aVO.oriNo);
		if(isHit == true) {
			//	��ȸ�� ���� �Լ��� ȣ���Ѵ�.
			abService.updateHit(aVO.oriNo);
		}
		//		�並 �θ���.
		RedirectView	rv = new RedirectView("../AnBoard/BoardView.sun");
		rv.addStaticAttribute("nowPage", aVO.nowPage);
		rv.addStaticAttribute("oriNo", aVO.oriNo);
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		return mv;
	}
	
	//	�󼼺��� ��û�� ó���� ��Ʈ�ѷ� �Լ�
	@RequestMapping("/BoardView")
	public ModelAndView boardView(AnBoardVO aVO) {
		//	����
		//		�Ķ���� �ް�
		//		�󼼺��� ���� ���ϰ�
		//		�� ���ۿ� �޸� ����� ���ϰ�
		HashMap	map = abService.boardView(aVO.oriNo);
		//		�並 �θ���.
		ModelAndView	mv = new ModelAndView();
		//	Map���� ���ΰ��� �и��ؼ� �𵨷� �����ص� �ȴ�.
//		mv.addObject("VIEW", map.get("VIEW"));
//		mv.addObject("LIST", map.get("LIST"));

		mv.addObject("MAP", map);
		//	��û ���踦 �� �� nowPage�� �� ��Ʈ�ѷ��� �ʿ��ؼ� �������� �ƴϰ�
		//	������ ���ؼ� ������ ��Ų �Ķ���� �̹Ƿ�
		//	�������� ������ �ݵ�� �����ؾ� �Ѵ�.
		mv.addObject("nowPage", aVO.nowPage);
		mv.setViewName("AnBoard/BoardView");
		return mv;
	}
	
	//	��� ���⸦ ���� �� ��û ó�� �Լ�
	@RequestMapping("/AnWriteForm")
	public ModelAndView AnWriteForm(AnBoardVO aVO) {
		//	�Ķ���� �ް�
		
		//	�ʿ��ϴٸ� ���� �����ϰ�(�α����� �� ����� �� �� �����ϱ�)
		
		//	�並 �θ���.
		ModelAndView	mv = new ModelAndView();
		mv.addObject("oriNo", aVO.oriNo);
		mv.addObject("nowPage", aVO.nowPage);
		mv.setViewName("AnBoard/AnWriteForm");
		return mv;
	}
	
	//	��� ���� ��û ó�� �Լ�
	@RequestMapping("/AnWriteProc")
	public ModelAndView AnWriteProc(AnBoardVO aVO, HttpSession session) {
		//	����
		//		�Ķ���� �ް�
		//		VO�� �̿��ؼ� �Ķ���͸� �޴� ��쿡��
		//		�Ķ���͸� ���� �� �ִ� setXxx()�� VO Ŭ������ �����ϴ��� Ȯ���ؾ� �Ѵ�.
		
		//		���� ���� ����
		//		(�ֳ��ϸ� ��ۿ� ����� Group, Step, Order�� ������ �̿��ؼ� ������ �Ѵ�.)
		//			�츮�� �󼼺��� ���� ����� �� ����� �����̴�.
		AnBoardVO	vo = abService.getOriInfo(aVO.oriNo);
		
		//		�� ������ �̿��ؼ� �ʿ��� ������ �����ϰ�
		//		�ʿ��� ������ Group, Step, Order�̴�.
		int		newGroup = vo.bgroup;
		int		newStep = vo.bstep + 1;
		int		newOrder = vo.border + 1;

		//		�����ͺ��̽� �����Ѵ�.
		//		�Ķ���ͷ� ���� VO �ȿ��� Ŭ���̾�Ʈ�� �˷��� �����͸� �����Ѵ�.
		//		����, ����, ���, �±״� �����Ѵ�.
		//		������ �۾���, �׷�, ����, ������ �������� �ʴ´�.
		//	������ �����͸� ä���� ������ �Ѵ�.
		String	id = (String) session.getAttribute("UID");
		aVO.writer = id;
		aVO.bgroup = newGroup;
		aVO.bstep = newStep;
		aVO.border = newOrder;

		//	���
		//		Ư�� ���Ǹ� ������ ��쿡��
		//		�� ���� ��ɿ� �ʿ��� �����Ͱ� �Ϻ��ϰ� �غ� �Ǿ����� 
		//		�и��� Ȯ���ؾ� �Ѵ�.
		abService.anIsert(aVO);
		
		//	���� aVO�� no���� ���� �Է��� ����� ��ȣ�� �ԷµǾ� �ִ�.
		//		���� ������ �Ѵ�.
		
		
		//	�並 �θ���.
		RedirectView	rv = new RedirectView();
		rv.addStaticAttribute("nowPage", aVO.nowPage);
		rv.addStaticAttribute("bgroup", aVO.bgroup);
		rv.addStaticAttribute("oriNo", aVO.oriNo);
		//	no ������ ���� ����� ������ �� <selectKey�� ���ؼ� ����� �Է¾�����.
//		rv.addStaticAttribute("oriNo", aVO.no);
		rv.setUrl("../AnBoard/BoardView.sun");
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		return mv;
	}
	
	//	�˻� ��û ó���Լ�
	@RequestMapping("BoardSearch")
	public ModelAndView boardSearch(AnBoardVO aVO) {
		//	�Ķ���� �ް�

		//	�˻��ϰ�
		ArrayList	list = abService.boardSearch(aVO);
		//	�������.
		ModelAndView	mv = new ModelAndView();
		mv.addObject("LIST", list);
		mv.setViewName("AnBoard/BoardSearch");
		return mv;
	}
	
	//	�����ϱ� �� ��û ó�� �Լ�
	@RequestMapping("/ModifyForm")
	public ModelAndView modifyForm(AnBoardVO aVO) {
		//	����
		//		�Ķ���� �ް�
		
		//		�����ϱ⸦ ���ؼ� ���� ���� �˷��ش�.(��񿡼� �˾Ƴ���.)
		//		����� ������ �󼼺��⿡�� ���� �� ������ �˾Ƴ��� ó���� �س��Ҵ�.
		//		�̰��� �̿��ϸ� �ɰ��̴�.
		AnBoardVO vo = abService.getModifyView(aVO.oriNo);
		
		//		�並 �θ���.
		ModelAndView mv = new ModelAndView();
		mv.addObject("VIEW", vo);
		mv.addObject("nowPage", aVO.nowPage);
		mv.setViewName("AnBoard/ModifyForm");
		return mv;
	}
	
	//	�����ϱ� ��û ó���Լ�
	@RequestMapping("/ModifyProc")
	public ModelAndView modifyProc(AnBoardVO aVO) {
		//	����
		//		�Ķ���� �ޱ�
		
		//		���� ó���ϱ�
		int		count = abService.updateBoard(aVO);
//		if(count == 0) {
//			//	����� �޶� ���� �ȵ�
//		}
//		else {
//			//	����� ���Ƽ� ���� ��
//		}
		
		//		�� �θ���
		ModelAndView		mv = new ModelAndView();
		mv.addObject("nowPage", aVO.nowPage);
		mv.addObject("oriNo", aVO.oriNo);
		mv.addObject("count", count);
		mv.addObject("from", "update");
		mv.setViewName("AnBoard/ImsiView");
		//	�ӽú信���� ���� ���θ� �˷��ְ�
		//	���� �󼼺���� �����ϹǷ�
		//	�󼼺��� �ʿ��� oriNo, nowPage�� �˷��� �ʿ䰡 �ִ�.
		//	�ӽú信���� ���� ���θ� �̿��ؼ� alert â�� ����ؾ� �ϹǷ�
		//	���� ���θ� �Ǵ��ϴ� � ������� �˷��� �ʿ䰡 �ִ�.
		return mv;
	}
	
	//	���� ��û ó���Լ�
	@RequestMapping("/DeleteProc")
	public ModelAndView deleteProc(AnBoardVO aVO) {
		
		int		count = abService.deleteBoard(aVO);
		
		ModelAndView		mv = new ModelAndView();
		mv.addObject("nowPage", aVO.nowPage);
		mv.addObject("oriNo", aVO.oriNo);
		mv.addObject("count", count);
		mv.addObject("from", "delete");
		mv.setViewName("AnBoard/ImsiView");
		return mv;
	}
}
















