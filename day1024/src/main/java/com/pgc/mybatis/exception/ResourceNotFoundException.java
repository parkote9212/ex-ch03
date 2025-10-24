package com.pgc.mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 이 예외가 발생하면 404 응답
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }

}
/*

1. 서비스 레이어의 예외 처리 (Null 대신 예외 사용)
        현재 ProServ의 getProductId나 updateProduct는 대상이 없으면 null을 반환합니다. 그리고 ProCon과 ProRestCon 컨트롤러 양쪽에서 이 null을 체크하는 로직이 중복으로 들어가 있습니다.
        개선 방향: 서비스 레이어에서 null을 반환하는 대신, 명시적인 예외(Exception)를 발생시킵니다. 그리고 @ControllerAdvice라는 글로벌 예외 핸들러를 만들어 이 예외를 한곳에서 처리합니다.
        장점:
        컨트롤러 코드가 훨씬 깔끔해집니다. (null 체크 로직 제거)
        예외 처리가 한곳으로 중앙화되어 유지보수가 쉬워집니다.
        "데이터가 없음"이라는 비즈니스 상황을 명확한 예외로 표현할 수 있습니다.
        */
