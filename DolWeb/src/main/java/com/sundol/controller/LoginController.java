package com.sundol.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sundol.Service.LoginService;

//	이 클래스가 컨트롤러 역활을 하기 위한 2가지 조건
//	1.	반드시 base-package 하위에 만들어야 한다.
//	2.	@Controller 어노테이션을 이용해서 컨트롤러 선언을 해야 한다.
@Controller
public class LoginController {
	
	@Autowired
	public LoginService 	lService;
	
	
	
	//	실제로 요청과 맵핑되는 것은 함수 단위이다.
	//	요청 처리 함수를 만드는 방법
	//		함수는 보통 함수로 만들면 된다.(반환값, 파라메터에 관련된 규칙이 없다.)
	//		대신에 @RequsetMapping 어노테이션을 이용해서
	//		이 함수가 언제 실행될지 요청과 맵핑을 시키면 된다.
	//	참고
	//		요청 등록을 하는 방법
	//		http://localhost:8080/dol/Login/LoginForm.sun인데
	//		이중에 http://localhost:8080/dol		는 도메인으로 빠지는 부분이다.
	//				.sun							는 우리가 서블릿 등록을 할 때 규칙을 정한것이다.
	//		그러므로 약속된 두개를 뺀 나머지를 등록하면 되는것이다.
	
	//		혹시라도 서블릿 등록을 할 때 "/"로 등록하면 확장자가 약속되지 않았으므로
	//		확장자까지 명시해야 한다.
	@RequestMapping("/Login/LoginForm")
	public String loginForm() {
		//	이 안에서 해야할 일은 일반 JSP 시간에 배웠던 컨트롤러에서 하는일과
		//	동일하게 코딩하면 된다.
		//		파라메타 받기
		//		데이터베이스 처리하기
		//		모델 생산하기
		//		뷰 호출
		return "Login/LoginForm";
		//	이 경우 실제 뷰는 /WEB-INF/views/Login/LoginForm.jsp가 된다.
	}
	
	//	로그인 처리를 위한 함수를 제작
	@RequestMapping("/Login/LoginProc")
	public ModelAndView loginProc(HttpServletRequest req, HttpSession session) {
		//	할일
		//		파라메터 받고
		//		기본 파라메터를 받는 방법
		//		1.	req를 이용해서 getParameter()로 받는 방법
		//			이 방법을 이용하기 위해서는 HttpServletRequest라는 클래스가 필요하다.
		
		//			스프링은 진짜 좋은점이 어떤 클래스가 작업을 할 때 필요하면
		//			함수의 매개변수()안에 그 클래스를 기록만 하면 알려주도록 내부적으로
		//			약속이 되어있다.
		//			
		//			파라메터의 순서도 관계가 없다.
		String		id = req.getParameter("id");
		String		pw = req.getParameter("pw");
		
		//		2.	VO 클래스를 이용해서 받는 방법
		
		//		데이터베이스 처리하고(그런 회원이 있는지 확인하고)
		//		데이터베이스 처리를 하기 위해서는
		//		1.	SQL 파일			질의 명령을 모아놓을 파일
		//		2.	DAO 파일			질의를 실행해 줄 파일
		//		3.	Service 파일		비지니스 로직을 처리할 파일
		int result = lService.loginProc(id, pw);
		//		데이타베이스 처리 결과가 0이면 그런 회원이 없는 것이고
		//		처리 결과가 1이면 그런 회원이 있는 것이다.
		
		if(result == 1) {
			//	로그인에 성공한 경우이다.
			//	로그인에 성공하면 세션에 그 회원의 정보를 기억해서 다른 곳에서도
			//	사용할 수 있도록 조치한다.
			session.setAttribute("UID", id);
		}
		else {
			//	로그인에 실패한 경우이다.
			//	솔찍히 할일이 없다.
		}
		
		
		//		결과를 모델로 만들어서
		//		뷰를 호출한다.
		
		//	이 작업은 로그인을 해 달라는 작업이다.
		//	이 작업이 끝나면 우리는 보나마다 다른 화면을 출력하기를 원할 것이다.
		//	과거에는 임시뷰를 호출하고 그 임시뷰에서
		//	<redirect url="다른 요청"/>		을 이용해서 강제로 Redirect를 시켰다.
		
		//	스프링에서는 뷰를 호출할 때 직접 Redirect 뷰를 사용할 수 있다.
		//	방법
		//	Redirect 시킬 요청을 등록한다.
		RedirectView	rv = new RedirectView("../Login/LoginForm.sun");
		
		//	ModelAndView에 redirect 뷰 등록을 한다.
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		
		return mv;
	}
	
	//	로그 아웃 처리함수
	@RequestMapping("/Login/Logout")
	public ModelAndView logout(HttpSession session, RedirectView rv, ModelAndView mv) {
		//	할일
		//		세션을 지운다.
		session.removeAttribute("UID");
		//		뷰를 부른다.
		rv.setUrl("../Login/LoginForm.sun");
		mv.setView(rv);
		return mv;
	}
}









