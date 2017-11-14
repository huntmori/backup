package com.sundol.UTIL;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/*
 * 	이 클래스는 파일 업로드시 발생하는 여러 절차를 처리하기 위한 함수로 구성된
 * 	유틸리티 클래스이다.
 */
public class FileUtil {
	/*
	 * 	실제 업로드를 진행할 함수
	 */
	public static String upload(MultipartFile oriFile, String name, String path) {
		//	업로드를 실시할 때 이름이 바뀔 수 있으므로
		//	이름이 바뀐 경우라면 그 이름을 받아야 컨트롤러가 기억할 수 있다.
		
		//	oriFile			임시 저장된 첨보 파일의 정보를 기억하고 있는 클래스
		//	name, path	임시 파일을 복사할 위치와 복사할 파일의 이름을 기억할 변수
		
		//	복사를 하기 전에 지금 복사할 이름이 중복되면 이름을 바꾸어서 복사를
		//	해야할 것이다.
		name = renameFile(name, path);
		
		//	1.	복사할 파일을 File 클래스로 만든다.
		File		copyFile = new File(path + "\\" + name);
		//	2.	복사를 시도한다.
		try {
			oriFile.transferTo(copyFile);
		}
		catch(Exception e) {
			System.out.println("파일 업로드 에러 = " + e);
		}
		return name;
	}
	/*
	 * 	업로드 할 때 파일의 이름이 중복되는 경우 처리할 함수
	 * 	즉 지금 업로드할 파일의 이름이 이미 존재하면 이름을 변경해서 저장할 목적으로
	 * 	이름을 변경해 줄 파일
	 */
	public static String renameFile(String name, String path) {
		//	반환값			변경된 파일 이름
		//	파라메터		변경할 파일의 이름과 저장 경로
		
		//	규칙		이름이 중복되면 이름 뒤에 _번호를 이용해서 이름을 변경하도록 한다.
		//				예>		hong.txt		hong_1.txt로 바꿀 예정이다.
		int		count = 0;			//	뒤에 붙을 번호를 관리하기 위한 변수
		String	oriName = name;
		File		file = new File(path + "\\" + oriName);
		while(file.exists()) {
			//	이 파일은 이름을 바꾸어서 알려주어야 한다.
			//	방법	1	.을 이용해서 앞의 내용과 뒤의 내용을 분리한다.
			//			hong.txt		hong		txt		분리한다.
			int	pos = name.lastIndexOf(".");
			String	first = name.substring(0, pos);
			String	last = name.substring(pos + 1);
			
			//			2.	이 결과에 _번호를 붙여서 다시 재 조립한다.
			count++;		//	1
			first = first + "_" + count;			//	hong_1
			oriName = first + "." + last;			//	hong_1.txt
			
			//	근데
			//	이 이름도 있을 수 있다.
			//	그러므로 다시 검사하도록 하자.
			file = new File(path + "\\" + oriName);
		}
		return oriName;
	}
}
