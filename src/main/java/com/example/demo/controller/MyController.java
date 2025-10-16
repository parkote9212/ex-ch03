package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MyService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MyController {

	private static final Logger log = LoggerFactory.getLogger(MyController.class);
	private final MyService myService;

	public MyController(MyService myService) {

		this.myService = myService;

	}

	// 1. 루트 경로("/")에 대한 GetMapping 추가
	@GetMapping("/")
	public List<String> serviceMethodList() {
		log.info("===== 루트 컨트롤러 (메소드 목록) =====");
		return myService.getMethodList(); // 2. 서비스를 호출해 메소드 목록을 가져옴
	}

	@GetMapping("/sayHello")
	public String sayHello(@RequestParam("name") String name) {
		log.info("===== hello 컨트롤러  =====");
		return myService.sayHello(name);
	}

	@GetMapping("/ifElse")
	public String ifElse(@RequestParam("num") int num) {
		log.info("===== ifElse 컨트롤러 =====");
		return myService.ifElse(num);
	}

	@GetMapping("/exFor")
	public List<String> exFor() {
		log.info("===== for 컨트롤러 =====");
		return myService.exFor();
	}

	@GetMapping("/numList")
	public List<String> numList(@RequestParam("num") int num) {
		log.info("===== numlist 컨트롤러 =====");
		return myService.numList(num);
	}

	@GetMapping("/exWhile")
	public List<String> exWhile() {
		log.info("===== exWhile 컨트롤러 =====");
		return myService.exWhile();
	}

	@GetMapping("/api1")
	public String apiStr1() {
		log.info("===== apiStr 컨트롤러 =====");
		return myService.apiStr1();
	}

	@GetMapping("/exForEach")
	public String exForEach() {
		log.info("===== exForEach 컨트롤러 =====");
		return myService.exForEach();
	}

	@GetMapping("/exFilter")
	public String exFilter() {
		log.info("===== filter 컨트롤러 =====");
		return myService.exFilter();
	}

	@GetMapping("/exDistinct")
	public String exDistinct() {
		log.info("===== distinct 컨트롤러 =====");
		return myService.exDistinct();
	}
	
	@GetMapping("/exMap")
	public String exMap() {
		log.info("===== map 컨트롤러 =====");
		return myService.exMap();
	}
	
//	3-3-9. java if문사용 null 체크
	@GetMapping("/exNull1")
	public String exNull1() {
		log.info("===== exNull1 컨트롤러 =====");
		return myService.exNull1();
	}

//	3-3-11. 비어있는 Optional 반환
	@GetMapping("/exNull2")
	public String exNull2() {
		log.info("===== exNull2 컨트롤러 =====");
		return myService.exNull2();
	}
}
