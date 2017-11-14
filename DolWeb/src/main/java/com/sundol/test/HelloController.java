package com.sundol.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	//	�� �ȿ��� ������ ��û�� ó���� �Լ��� ����� ���� �� �ִ�.
	//	�������� �ϳ��� ��û�� �ϳ��� ��Ʈ�ѷ��� ����ϴ� ���� �ƴϰ�
	//	�ϳ��� ��û�� �ϳ��� �Լ� ������ ó���ȴ�.
	//	��� ������ �Լ����� ��û URL�� �����ϸ� �ȴ�.
	
	@RequestMapping("Hello")
	public String hello() {
		//	�� �ȿ����� JSP �ð��� ó���� �Ͱ� ���� ��û�� ó����
		//	����Ͻ� ������ �����ϸ� �ȴ�.
		System.out.println("Hello ��û");
		//	����Ͻ� ������ ������ ���� �����ؼ� �信�� �����ϰ�
		
		//	�並 ȣ���Ѵ�.
		return "Test/Hello";
		//	�̷��Ը� �ϸ� ���� ȯ�漳�� ���뿡 ���ؼ�
		//	���� ��� /WEB-INF/views/Test/Hello.jsp		�� �ȴ�.
		
	}
	@RequestMapping("Test")
	public String hello1() {
		System.out.println("Test ��û");
		return "Test/Hello";
	}
	@RequestMapping("Sample")
	public String hello2() {
		System.out.println("Sample ��û");
		return "Test/Hello";
	}
}



