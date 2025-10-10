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
		public List<String> serviceMethodList(){ 
			log.info("===== 루트 컨트롤러 (메소드 목록) =====");
			return myService.getMethodList(); // 2. 서비스를 호출해 메소드 목록을 가져옴
		}
	
	@GetMapping("/sayHello")
	public String root(@RequestParam("name") String name){ 
		log.info("===== hello 컨트롤러  =====");
		return myService.sayHello(name);
	}
	
	@GetMapping("/ifElse")
	public String ifElse(@RequestParam("num") int num){ 
		log.info("===== ifElse 컨트롤러 =====");
		return myService.ifElse(num);
	}
	
	@GetMapping("/exFor")
	public List<String> exFor(){ 
		log.info("===== for 컨트롤러 =====");
		return myService.exFor();
	}
	
	@GetMapping("/numList")
	public List<String> numList(@RequestParam("num") int num){ 
		log.info("===== numlist 컨트롤러 =====");
		return myService.numList(num);
	}
	
	@GetMapping("/exWhile")
	public List<String> exWhile(){ 
		log.info("===== exWhile 컨트롤러 =====");
		return myService.exWhile();
	}
	

}
