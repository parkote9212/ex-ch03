package com.example.demo.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	private static final Logger log = LoggerFactory.getLogger(MyService.class);
	
	public List<String> getMethodList() {
		log.info("===== getMethodList 서비스 (리플렉션) =====");
		List<String> methodList = new ArrayList<>();

		try {
			// 1. MyService 클래스의 메타데이터(정보)를 가져옵니다.
			Class<?> serviceClass = this.getClass();
			
			// 2. 클래스에 정의된 모든 메소드의 목록을 배열로 가져옵니다.
			Method[] methods = serviceClass.getDeclaredMethods();
			
			// 3. 각 메소드를 순회하며 이름을 리스트에 추가합니다.
			for (Method method : methods) {
				methodList.add(method.getName());
			}
			
		} catch (Exception e) {
			log.error("메소드 목록을 가져오는 중 오류 발생", e);
		}
		
		return methodList;
	}

	public String sayHello(String str) {
		// TODO Auto-generated method stub

		String msg = "안녕, " + str;
		log.info("===== Hello 서비스 =====");
		log.info(msg);
		return msg;
	}

	public String ifElse(int num) {
		String result;
		// TODO Auto-generated method stub
		if (num == 1) {

			result = "if블록입니다.";
		} else if (num == 2) {

			result = "else if블록입니다.";
		} else {

			result = "else 블록입니다.";
		}
		log.info(result);
		return result;
	}

	// 1. 반환 타입을 int[]에서 List<String>으로 변경
	public List<String> exFor() {
		log.info("===== exFor 서비스 =====");
		int[] array = { 1, 2, 3, 4, 5 };

		// 2. 결과를 담을 문자열 리스트 생성
		List<String> resultList = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			// 3. 화면에 보낼 문자열 생성
			String msg = "i = " + array[i];

			// 4. 로그에도 남기고, 결과 리스트에도 추가
			log.info(msg);
			resultList.add(msg);
		}

		// 5. 숫자 배열 대신 문자열 리스트를 반환
		return resultList;
	}

	public List<String> numList(int num) {
		// TODO Auto-generated method stub
		log.info("===== numList 서비스 =====");
		log.info(num + "개의 수를 가진 배열생성");

		List<String> list = new ArrayList<>();

		for (int i = 1; i <= num; i++) {
			String msg = "list[" + (i - 1) + "]=" + i;

			list.add(msg);
			log.info(msg);
		}

		// 4. 완성된 리스트를 반환합니다.
		return list;
	}

	public List<String> exWhile() {
		// TODO Auto-generated method stub
		log.info("===== exWhile 서비스 =====");
		int[] array = { 1, 2, 3, 4, 5 };
		List<String> list = new ArrayList<>();
		int i = 0;

		while (i < array.length) {
			String msg = "i = " + array[i] ;
			log.info(msg);
			list.add(msg);
			i++;
			}
			return list;
	}

}
