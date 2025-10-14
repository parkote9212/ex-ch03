package com.example.demo.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
			String msg = "i = " + array[i];
			log.info(msg);
			list.add(msg);
			i++;
		}
		return list;
	}

	public String apiStr1() {
		log.info("===== api스트림1 서비스 =====");
		// TODO Auto-generated method stub
//		foreach
		log.info("foreach");
		List list = new ArrayList<String>();

		list.add("public");
		list.add("static");
		list.add("void");

		list.stream().forEach(str -> log.info((String) str));

//		evenlist
		log.info("evenlist");

		Integer[] integerArray = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		List<Integer> list2 = Arrays.asList(integerArray);

		List<Integer> evenList = list2.stream().filter(value -> value % 2 == 0).collect(Collectors.toList());

		evenList.stream().forEach(value -> log.info(String.valueOf(value)));
		return "foreach" + " evenlist";

	}

	public String exForEach() {
		// TODO Auto-generated method stub
		Integer[] integerArray = new Integer[] { 1, 2, 3, 4, 5 };
		List<Integer> list = Arrays.asList(integerArray);
		list.stream().forEach(value -> log.info(String.valueOf(value)));
		return "foreach 예제";
	}

// gpt에 리팩토링요청 변수 최소화
//	솔직히 깔끔해졌는지 모르겠다.
	public String exFilter() {
		// TODO Auto-generated method stub
		List<Integer> evenList = IntStream.rangeClosed(1, 10).filter(num -> num % 2 == 0)
				// IntStream을 Stream<Integer>로 변환
				.boxed().collect(Collectors.toList());
		evenList.stream().forEach(value -> log.info(String.valueOf(value)));

		return "filter 예제";
	}

//	위에 예제처럼 변수최소화 하기
	public String exDistinct() {
		// TODO Auto-generated method stub
		List<Integer> numberList = List.of(1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4);
		List<Integer> distinctList = numberList.stream().distinct().toList();
		distinctList.stream().forEach(value -> log.info(String.valueOf(value)));
		return "중복제거 예제";
	}

	public String exMap() {
		// TODO Auto-generated method stub
		String[] lowercaseArray = new String[] {"public", "static", "void"};
		List<String> lowercaseList = Arrays.asList(lowercaseArray);
		List<String> uppercaseList = lowercaseList.stream()
				.map(value -> value.toUpperCase()).toList();
//		람다를 메소드참조로 String.aluof()는 log.info()가 내부적으로 처리가능
		uppercaseList.stream().forEach(log::info);
		return "컬렉션의 요소들에 특정 연산을 적용한 새로운 스트림 생성한 Map 스트림";
	}

	private static String getSomeStr1() {
		return null;
	}
	public String exNull1() {
		// TODO Auto-generated method stub
		String isThisNull = getSomeStr1();
		
		if(null != isThisNull) {
			log.info(isThisNull.toUpperCase());
		}
		return "if문을 사용한  null 체크";
	}
	
//	Optional 사용 null체크
	private static Optional<String> getSomeStr2(){
//		null 반환이 아닌 비어있는 옵션을 반환
//		return Optional.empty();
		return Optional.ofNullable("public static void");
	}
	
	public String exNull2() {
		Optional<String> isThisNull = getSomeStr2();
		
		isThisNull.ifPresent(str -> log.info(str.toUpperCase()));
	return "Optional 사용한 Nullcheck";
	}
	
//	collect Collectors 대신 .toList로 생성가능
	
	

}
