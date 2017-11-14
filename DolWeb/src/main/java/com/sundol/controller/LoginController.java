package com.sundol.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sundol.Service.LoginService;

//	�� Ŭ������ ��Ʈ�ѷ� ��Ȱ�� �ϱ� ���� 2���� ����
//	1.	�ݵ�� base-package ������ ������ �Ѵ�.
//	2.	@Controller ������̼��� �̿��ؼ� ��Ʈ�ѷ� ������ �ؾ� �Ѵ�.
@Controller
public class LoginController {
	
	@Autowired
	public LoginService 	lService;
	
	
	
	//	������ ��û�� ���εǴ� ���� �Լ� �����̴�.
	//	��û ó�� �Լ��� ����� ���
	//		�Լ��� ���� �Լ��� ����� �ȴ�.(��ȯ��, �Ķ���Ϳ� ���õ� ��Ģ�� ����.)
	//		��ſ� @RequsetMapping ������̼��� �̿��ؼ�
	//		�� �Լ��� ���� ������� ��û�� ������ ��Ű�� �ȴ�.
	//	����
	//		��û ����� �ϴ� ���
	//		http://localhost:8080/dol/Login/LoginForm.sun�ε�
	//		���߿� http://localhost:8080/dol		�� ���������� ������ �κ��̴�.
	//				.sun							�� �츮�� ���� ����� �� �� ��Ģ�� ���Ѱ��̴�.
	//		�׷��Ƿ� ��ӵ� �ΰ��� �� �������� ����ϸ� �Ǵ°��̴�.
	
	//		Ȥ�ö� ���� ����� �� �� "/"�� ����ϸ� Ȯ���ڰ� ��ӵ��� �ʾ����Ƿ�
	//		Ȯ���ڱ��� ����ؾ� �Ѵ�.
	@RequestMapping("/Login/LoginForm")
	public String loginForm() {
		//	�� �ȿ��� �ؾ��� ���� �Ϲ� JSP �ð��� ����� ��Ʈ�ѷ����� �ϴ��ϰ�
		//	�����ϰ� �ڵ��ϸ� �ȴ�.
		//		�Ķ��Ÿ �ޱ�
		//		�����ͺ��̽� ó���ϱ�
		//		�� �����ϱ�
		//		�� ȣ��
		return "Login/LoginForm";
		//	�� ��� ���� ��� /WEB-INF/views/Login/LoginForm.jsp�� �ȴ�.
	}
	
	//	�α��� ó���� ���� �Լ��� ����
	@RequestMapping("/Login/LoginProc")
	public ModelAndView loginProc(HttpServletRequest req, HttpSession session) {
		//	����
		//		�Ķ���� �ް�
		//		�⺻ �Ķ���͸� �޴� ���
		//		1.	req�� �̿��ؼ� getParameter()�� �޴� ���
		//			�� ����� �̿��ϱ� ���ؼ��� HttpServletRequest��� Ŭ������ �ʿ��ϴ�.
		
		//			�������� ��¥ �������� � Ŭ������ �۾��� �� �� �ʿ��ϸ�
		//			�Լ��� �Ű�����()�ȿ� �� Ŭ������ ��ϸ� �ϸ� �˷��ֵ��� ����������
		//			����� �Ǿ��ִ�.
		//			
		//			�Ķ������ ������ ���谡 ����.
		String		id = req.getParameter("id");
		String		pw = req.getParameter("pw");
		
		//		2.	VO Ŭ������ �̿��ؼ� �޴� ���
		
		//		�����ͺ��̽� ó���ϰ�(�׷� ȸ���� �ִ��� Ȯ���ϰ�)
		//		�����ͺ��̽� ó���� �ϱ� ���ؼ���
		//		1.	SQL ����			���� ����� ��Ƴ��� ����
		//		2.	DAO ����			���Ǹ� ������ �� ����
		//		3.	Service ����		�����Ͻ� ������ ó���� ����
		int result = lService.loginProc(id, pw);
		//		����Ÿ���̽� ó�� ����� 0�̸� �׷� ȸ���� ���� ���̰�
		//		ó�� ����� 1�̸� �׷� ȸ���� �ִ� ���̴�.
		
		if(result == 1) {
			//	�α��ο� ������ ����̴�.
			//	�α��ο� �����ϸ� ���ǿ� �� ȸ���� ������ ����ؼ� �ٸ� ��������
			//	����� �� �ֵ��� ��ġ�Ѵ�.
			session.setAttribute("UID", id);
		}
		else {
			//	�α��ο� ������ ����̴�.
			//	������ ������ ����.
		}
		
		
		//		����� �𵨷� ����
		//		�並 ȣ���Ѵ�.
		
		//	�� �۾��� �α����� �� �޶�� �۾��̴�.
		//	�� �۾��� ������ �츮�� �������� �ٸ� ȭ���� ����ϱ⸦ ���� ���̴�.
		//	���ſ��� �ӽú並 ȣ���ϰ� �� �ӽú信��
		//	<redirect url="�ٸ� ��û"/>		�� �̿��ؼ� ������ Redirect�� ���״�.
		
		//	������������ �並 ȣ���� �� ���� Redirect �並 ����� �� �ִ�.
		//	���
		//	Redirect ��ų ��û�� ����Ѵ�.
		RedirectView	rv = new RedirectView("../Login/LoginForm.sun");
		
		//	ModelAndView�� redirect �� ����� �Ѵ�.
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		
		return mv;
	}
	
	//	�α� �ƿ� ó���Լ�
	@RequestMapping("/Login/Logout")
	public ModelAndView logout(HttpSession session, RedirectView rv, ModelAndView mv) {
		//	����
		//		������ �����.
		session.removeAttribute("UID");
		//		�並 �θ���.
		rv.setUrl("../Login/LoginForm.sun");
		mv.setView(rv);
		return mv;
	}
}









