package com.sundol.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Controller;
import 	org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sundol.Service.FileBoardService;
import com.sundol.UTIL.FileUtil;
import com.sundol.UTIL.PageUtil;
import com.sundol.UTIL.SessionUtil;
import com.sundol.UTIL.StringUtil;
import com.sundol.VO.FileBoardVO;

@Controller
//	�� ��Ʈ�ѷ��� ���� ���ε�� ��Ʈ�ѷ� ��Ȱ�� �ϵ��� ����߹Ƿ�..
//	���� ���ε� ��û�� ���� ������ �̸� ���� �־ ���� ���ʹ� ����� ���븸
//	ó���ϵ��� ����.
@RequestMapping("/FileBoard")
public class FileBoardController {
	//	��Ʈ�ѷ������� ���� Ŭ������ �̿��ؼ� ó���ϱ�� �����Ƿ�....
	@Autowired
	private 	FileBoardService		fService;
	
	
	
	/*
	 * �Խù� ��� ���� ��û ó�� �Լ�
	 */
	@RequestMapping("/BoardList") 
	public ModelAndView	boardList(@RequestParam(value="nowPage", defaultValue="1") int nowPage) {
		//	����	
		//		�Ķ���� �ް�
		//		�Խù� ������
		PageUtil	pInfo = fService.getPageInfo(nowPage);
		ArrayList	list = fService.getBoardList(pInfo);
		
		//		�並 �θ���.
		ModelAndView	mv = new ModelAndView();
		//		������ �̵������ �Ҷ� ���� ���� �Ķ���͸� �����ؾ� �Ѵ�.
		//		nowPage�� ������ ��Ų���̴�.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("FileBoard/BoardList");
		return mv;
	}
	
	/*
	 * 	�۾��� �� ��û ó�� �Լ�
	 */
	@RequestMapping("/WriteForm")
	public ModelAndView	writeForm(HttpSession session) {
		//	�۾���� �α����� �� ����� ������ �غ���.
		//	�׷��� ���ؼ��� ���� �˻縦 ���־�� �Ѵ�.
		
		boolean	isLogin = SessionUtil.isLogin(session);
		if(!isLogin) {
			//	�α����ϵ��� �α��� ȭ������ �������� ����.
			ModelAndView	mv = new ModelAndView();
			RedirectView	rv = new RedirectView("../Login/LoginForm.sun");

			//	��ó�� �������� �ϴٰ� �α��� ȭ������ ������ ������....
			//	�α����� ���� ��� �ڵ����� �����ϴ��Ϸ� ������ �� �ֳ���?
			//	�� ��쿡�� ���� ��� ���¿��� �Դ��� �ڽ��� ����� URL�� ������.
//			rv.addStaticAttribute("URL", "../FileBoard/WriteForm.sun");
			//	�α��� ó�������� �̰��� �Ķ���ͷ� �޾� ���Ҵٰ�
			//	�α��� ó���� �����ϸ� �޾Ƴ��� URL�� Redirect ��Ű��
			//	�����ϴ� �Ϸ� �ٽ� �ڵ� ������ ���̴�.
			
			//	�α��� ��Ʈ�ѷ�����
			/*	public ModelAndView login(URL�� �Ķ���ͷ� �޾Ƴ��Ҵٰ�) {
			 * 
			 * 	�α��ο� �����ϸ�
			 * 
			 * 	RedirectView	rv = new RedirectView(URL);
			*/
			
			mv.setView(rv);
			return mv;
		}
		//	�α��ο� ������ ����̹Ƿ�....
		//	�並 �����ش�.
		
		ModelAndView	mv = new ModelAndView();
		mv.setViewName("FileBoard/WriteForm");
		return mv;
	}
	
	/*
	 * 	�۾��� ó�� ��û ó�� �Լ�
	 */
	@RequestMapping("/WriteProc")
	public ModelAndView writeProc(FileBoardVO fVO, HttpSession session) {
		//	Ȥ�ö� ������ ���� ����̶�� �۾��� ��ü�� ���ϵ��� �����ؾ� �Ѵ�.
		boolean	isLogin = SessionUtil.isLogin(session);
		if(!isLogin) {
			//	�α��� �ϵ��� ��ġ�Ѵ�.
			ModelAndView	mv = new ModelAndView();
			RedirectView	rv = new RedirectView("../Login/LoginForm.sun");
			mv.setView(rv);
			return mv;
		}
		//	�α����� �� ����̶�� �׻���� ���̵� �˾ƾ� �Խù��� ����� �Ұ��̴�.
		String	id = SessionUtil.getLoginId(session);
		
		
		//	����
		//		�Ķ���� �ޱ�
		//		���� ÷�ΰ� �ִ� ���
		//			1.	VO
		//			2.	@RequestParam		�� ���� �� �ִ�.
		//		����
		//			fileupload.jar ������ �̿��� �� ÷�ε� ������ �޴� ���
		//			÷�ε� ������ MultipartFile �̶�� Ŭ������ �޾ƾ� �Ѵ�.
		
		//	����	��ó�� ÷�� ������ �Ķ���͸� ������....
		//			÷�� ������ �ӽ� ������ҿ� ����Ǿ�����.
		//			(���� �ӽ� ���� ��Ҵ� �����ڰ� ������ �� �ִ�. ȯ�� ���� �κп���...)
		//			������ �ӽ� ���� ��Ҹ� ����ڰ� �����ߴٰ� �ؼ�
		//			���� ���ε尡 �Ǵ� ���� �ƴϴ�.
		//			�ֳ��ϸ� �ӽ� ����� ������ �� ��Ʈ�ѷ��� ������ ����Ǹ�
		//			�ڵ����� �����ȴ�.
		
		//			�� ��Ʈ�ѷ��� ��ġ�� ���� �ݵ�� �����ڰ�
		//			�ӽ� ���� ��ҿ� ����� ������ ���ϴ� ��ġ�� ������ ������ ���ƾ� �Ѵ�.
		//			�׷��� ������ ���ε尡 �Ϸ�ȴ�.
		
		String	path = "D:\\SpringUpload";
		//	������ �����ϴ� ��ġ�� �� ������ ������ ���� �޶�����.
		//	��, ���� �ٿ�ε� �������� ����� ����� �����ڰ� Ư�������� ���ϸ� �ȴ�.
		//	÷�ε� ������ WEB���� ����� �����̶�� �̰��� ������Ʈ �ȿ� ������ �Ѵ�.
		//	���
		//		1.	�� ������Ʈ �ȿ� ������ �����.
		//		2.	������Ʈ���� ���̴� ������ ���� �� ������ �����ϴ� ��ġ�� �ٸ��Ƿ�
//		String	path1 = session.getServletContext().getRealPath("image");
		//		�� �̿��ؼ� ���� ������ ����ϴ� ��ġ�� �˾Ƴ��� ����ؾ� �Ѵ�.
		
		
		
		//	������ ������ �̸��� �ӽ� ����� ������ �̸��� �����ϰ� �Ѵ�.
		
		//	�Ѱ��� ���ε�� ��� ó�� ���
//		String	name = fVO.getFiles()[0].getOriginalFilename();

		//	�������� �ѹ��� ���ε� �� ��� ó���ϴ� ���
		int		len = fVO.getFiles().length;
		//	==>		÷�ε� ������ ������ŭ �ݺ��ϸ鼭 ���ε� ��ų �κ�
		//			������ ������ ����� �𸣹Ƿ� ÷�� ������ ������ ����� ������
		//			�̸� ����� ����.
		ArrayList	fileInfoList = new ArrayList();
		for(int i = 0; i < len; i++) {
			String	name = fVO.getFiles()[i].getOriginalFilename();
			//	���� ����ڰ� ÷�� ���� ����� ����� ���� ������ ÷������ ������
			//	������ �̸��� �����Ƿ� ������ �߻��Ѵ�.
			//	�� ��쿡�� ������ ���� �̸��� ���� ������
			//	�̰��� �̿��ؼ� ó���ϸ� �ȴ�.
			
			//	�� ����� ���� ÷�ΰ� ������ ��쿡�� ����� �� �ִ�.
			if(StringUtil.isNull(name)) {
				continue;
			}
			String saveName = FileUtil.upload(fVO.getFiles()[i], name, path);
			//	���� �� ������ ���ε尡 �������Ƿ� ���ε�� ������ ������ �˾Ƴ���
			//	�׷��� ���߿� �� ������ �̿��ؼ� ��� ����� ���̹Ƿ�...
			
			//	�� ������ ������ ����� ����
			HashMap	map = new HashMap();
			map.put("oriName", fVO.getFiles()[i].getOriginalFilename());
			map.put("saveName", saveName);
			map.put("len", fVO.getFiles()[i].getSize());
			map.put("path", path);
			
			fileInfoList.add(map);
		}
		//	===>		���� ������ ��ġ�鼭 Ŭ���̾�Ʈ�� �� ������ ���ε尡 ������.
		//	Ŭ���̾�Ʈ�� ������ �Խù� ������ ������ ������ ��� ����ؾ� �Ѵ�.

		//	1.	������ ������ ����Ѵ�.
		
		//	2.	������ ������ ����Ѵ�.
		
		//	���⼭�� ����
		//		fVO�ȿ� insert �� �����Ͱ� �غ�Ǿ� �ִ��� Ȯ������.
		//	�׻� ������ �ʿ��� ��쿡�� ���� ������ �ϵ��� ����.
		//	���⼭�� �α��� ���� �˻縦 �ؼ� ó���ϵ��� �Ѵ�.
		//	�α����� ���̵� �۾��̷� ��ϵǾ�� �Ѵ�.
		fVO.setWriter(id);
		
		fService.insertBoard(fVO, fileInfoList);
		//	�並 �θ���.
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../FileBoard/BoardList.sun");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	�󼼺��� ��û ó�� �Լ�
	 */
	@RequestMapping("/BoardView")
	public ModelAndView boardView(FileBoardVO fVO, HttpSession session) {
		//	������ ��ȸ�� ������ ���־�� �ϴµ�...	�̰��� ������ �ϰ�
		
		//	����
		//	��ȸ�� ������ �����ͺ��̽��� �̿��ؼ� ó���ϴ� �����
		//	�Ϸ��Ѵ�.
		
		//	�󼼺��⸦ ���ؼ� ��񿡼� �����͸� ������.
		//	�̶� �󼼺��� ������
		//	������, ������ ������
		
		//	�˻������ ����, ������ �˻� �ܾ �˷��־�� �Ѵ�.
		//	�˻� �ܾ�� ������ ������ �ֵ��� ����߾���.
		
		HashMap map1 = new HashMap(); 
		map1.put("oriNo", fVO.getOriNo());
		map1.put("target", (String) session.getAttribute("target"));
		map1.put("word", (String) session.getAttribute("word"));
		
		HashMap	map = fService.getBoardView(fVO.getOriNo(), fVO.getKind(), map1);
		
		//	�並 ȣ���Ѵ�.
		ModelAndView		mv = new ModelAndView();
		mv.addObject("nowPage", fVO.getNowPage());
		mv.addObject("kind", fVO.getKind());
		//	���� ���� �����͸� �и��ؼ� �����ν� �信�� ���� ���ϰ� �۾��� �� �� �ֵ��� 
		//	��ġ����.
		mv.addObject("VIEW", map.get("VIEW"));
		mv.addObject("FILE", map.get("FILE"));
		mv.addObject("PRENEXT", map.get("PRENEXT"));
		mv.setViewName("FileBoard/BoardView");
		return mv;
	}
		
	/*
	 *	���� �ٿ�ε� ��û ó���Լ� 
	 */
	@RequestMapping("/FileDownload")
	public ModelAndView fileDownload(@RequestParam(value="oriNo") int oriNo) {
		//	����
		//		�ٿ�ε����� ������ ������ ��񿡼� ������
		FileBoardVO	fVO = fService.getDownloadInfo(oriNo);
		
		//		���� �� ������ �̿��ؼ� �ٿ�ε带 �ǽ��� �����̴�.
		//		�� �۾��� �ٷ� ����� �䰡 ���������� ó���� �����̴�.
		//		�信�� Model ������ ���ؼ� �ٿ�ε� ���� ������ ������ �˷��ָ� �ȴ�.
		
		//		�� ������ ��� �˷��ٰ� �ΰ��� ������ ���ε�...
		//		�츮�� �翬�� ���� �ٿ�ε尡 �����̹Ƿ�
		//		������ ������ File Ŭ������ �˷��ָ� ������ ����.
		File		downFile = new File(fVO.getPath() + "\\" + fVO.getSaveName());
		
		//		�信�� �˷��ش�.
		return new ModelAndView("download", "downloadFile", downFile);
		//		�Ķ����?
		//		1		����� �� ����(bean ó���� ����� �並 �̿��Ѵ�.)
		//				==>		bean ó���� �� ����� id���� �Է��Ѵ�.
		//		2		������ �Ķ����(����)�� ����� Ű��
		//		3		������ �Ķ����(����)
	}

	/*
	 * 	�˻� ��û ó�� �Լ�
	 */
	@RequestMapping("/BoardSearch")
	public ModelAndView boardSearch(FileBoardVO fVO, HttpSession session) {
		//	�˻� ��� ������ �̵� ����� �ʿ��ϸ�
		//	���� /FileBoard/BoardSearch.sun ��û�� �ؾ��� ���̰�
		//	�׷��� ���� �Ķ���͸� �����ؾ� �Ѵ�.
		//	�ʿ��� �Ķ���ʹ� ������ ���Ѿ� �Ѵ�.
	
		//	�����̵� �����Ͱ� ���� ���� ������ �۾��� ���� ����������.
		//	�̷� ��� ó���ϴ� �ٸ� ����� �ִ�.
		//		������ �̿��ϴ� ���
		//		����	ó�� �˻��� �õ��� �� �������� �����͸� ���ǿ� �Է��� ���´�.
		//				���� �˻�(������ �̵�)�� �õ��� ���� ���ǿ���
		//				�ʿ��� �����͸� �޾Ƽ� ����ϸ� �ȴ�.

		//	Ȥ�� ���� �������߿� �˻� ����� �����ϴϸ� �˾Ƴ���.
		//	���� �˻� ����� �����ϸ� �������� ó�� �˻��� �õ��ϴ� ���̴�.
		//	�� �����Ͱ� ������ �̰��� ������ �̵� ����� �õ��� ����� ���̴�.
		boolean	isTarget = StringUtil.isNull(fVO.getTarget());
		if(isTarget == true) {
			//	���������....
			fVO.setTarget((String) session.getAttribute("target"));
			fVO.setWord((String) session.getAttribute("word"));
		}
		//	��
		//	������ ���ؼ� ���ǿ� �ʿ��� �����͸� �Է��� ���ƾ� �Ѵ�.
		session.setAttribute("target", fVO.getTarget());
		session.setAttribute("word", fVO.getWord());
		
		//	�����ͺ��̽����� �˻�����.
		PageUtil	pInfo = fService.getSearchTotal(fVO);
		ArrayList	list = fService.getSearch(fVO, pInfo);
		
		//	�並 �ּ���
		ModelAndView	mv = new ModelAndView();
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("FileBoard/BoardSearch");
	
		return mv;
	}
}
