package com.pgc.cal.service;

import com.pgc.cal.dto.CalculationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class CalucatorServ {

    private static final Logger log = LoggerFactory.getLogger(CalucatorServ.class);

    public double calculate(CalculationRequest request) {
        String operator = request.getOperator();
        double num1 = request.getNum1();
        double num2 = request.getNum2();

 /*       switch (operator) {
            case "+":
                return this.add(num1, num2); // 내부 private 메서드 호출
            case "-":
                return this.subtract(num1, num2);
            case "*":
                return this.multiplication(num1, num2);
            case "/":
                return this.division(num1, num2);
            case "%":
                return this.mod(num1, num2);
            default:
                throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + operator);
        };*/
        
//        스위치 표현식 사용
        return switch (operator) {
            case "+" -> this.add(num1, num2);
            case "-" -> this.subtract(num1, num2);
            case "*" -> this.multiplication(num1, num2);
            case "/" -> this.division(num1, num2);
            case "%" -> this.mod(num1, num2);
            default -> throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + operator);
        };
    }


//calculate 메소드만 사용시 아래 함수는 private으로 변경해주면 좋다

    public double add(double i, double j) {

//        log에 string타입만 가능하여 형변환후 출력
//        log.info(String.valueOf(i + j));

//        SLf4J의 플레이스홀더 : 안에 값이 있는지 학인하고 실행한다.
        log.info("덧셈 결과: {}", (i+j));
        return (i + j);
    }


    public double subtract(double i, double j) {

        log.info("뺄셈 결과: {}", (i-j));
        return (i-j);
    }

    public double multiplication(double i, double j){

        log.info("곱셉 결과: {}", (i*j));
        return (i*j);
    }


    public double division(double i, double j) {
        if (j == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }
        log.info("나눗셈 결과: {}", (i/j));
        return (i/j);
    }

    public double mod(double i, double j) {
        if (j == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }
        log.info("나머지 결과: {}", (i%j));
        return (i%j);
    }

}
