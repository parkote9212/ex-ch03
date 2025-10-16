package com.pgc.cal.controller;


import com.pgc.cal.dto.CalculationRequest;
import com.pgc.cal.dto.CalculationResponse;
import com.pgc.cal.service.CalucatorServ;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("calculator")
public class CalculatorCon {

//    spring 방식1
//    @Autowired
//    CalucatorServ calucatorServ;

    //    spring방식2 : 생성자를 통한 객체주입 방식
    private final CalucatorServ calucatorServ;

    public CalculatorCon(CalucatorServ calucatorServ) {
        this.calucatorServ = calucatorServ;
    }
    
    


/*    @GetMapping("/add")
    public String add(){
        return new String("/덧셈");
    }*/

//    객체.메소드: 서비스 로직으로 연산결과를 리턴
//    @GetMapping("/add")
//    public double add(){
//        return calucatorServ.add(10,5);
//    }

    //    url로 매개변수 및 함수 호출
//    @GetMapping("/add")
//    public String add(@RequestParam("num1") double a, @RequestParam("num2") double b) {
//        double result = calucatorServ.add(a, b);
//        return a + " + " + b + " = " + result;
//    }

//    서비스 로직으로 연산결과를 리턴후, 결과포맷을 dto를 사용하겠다:  타입은 레코드
//    결과포맷을 dto타입으로 사용
//    return타입을 dto record타입으로 수정한다.

    @GetMapping("/add")
    public CalculationResponse add(@RequestParam("num1") double a, @RequestParam("num2") double b) {
        double result = calucatorServ.add(a, b);
        return new CalculationResponse(a,b,"+", result);
    }

//    @GetMapping("/sub")
//    public String subtract(@RequestParam("num1") double a, @RequestParam("num2") double b) {
//        double result = calucatorServ.subtract(a, b);
//        return a + " - " + b + " = " + result;
//    }

    @GetMapping("/sub")
    public CalculationResponse subtract(@RequestParam("num1") double a, @RequestParam("num2") double b) {
        double result = calucatorServ.subtract(a, b);
        return new CalculationResponse(a,b,"-", result);
    }

/*
    @GetMapping("/multi")
    public String multiplication(@RequestParam("num1") double a, @RequestParam("num2") double b) {
        double result = calucatorServ.multiplication(a, b);
        return a + " - " + b + " = " + result;
    }
*/

    @GetMapping("/multi")
    public String multiplication(@RequestParam("num1") double a, @RequestParam("num2") double b) {
        double result = calucatorServ.multiplication(a, b);
        return a + " * " + b + " = " + result;
    }

    @GetMapping("/div")
    public String division(@RequestParam("num1") double a, @RequestParam("num2") double b) {
        double result = calucatorServ.division(a, b);
        return a + " / " + b + " = " + result;
    }

    @GetMapping("/mod")
    public String mod(@RequestParam("num1") double a, @RequestParam("num2") double b) {
        double result = calucatorServ.mod(a, b);
        return a + " % " + b + " = " + result;
    }

//    @PostMapping("/calculate")
//    public CalculationResponse postMethodName(@RequestBody CalculationRequest request){
//        double result;
////        연산자값을 가져온다.
//        String operator = request.getOperator();
//
//        switch (operator) {
//            case "+":
//                result = calucatorServ.add(request.getNum1(), request.getNum2());
//                break;
//            case "-":
//                result = calucatorServ.subtract(request.getNum1(), request.getNum2());
//                break;
//            case "*":
//                result = calucatorServ.multiplication(request.getNum1(), request.getNum2());
//                break;
//            case "/":
//                result = calucatorServ.division(request.getNum1(), request.getNum2());
//                break;
//            case "%":
//                result = calucatorServ.mod(request.getNum1(), request.getNum2());
//                break;
//            default:
////                result = 0;
////                GPT검수결과 예외처리를 집어넣어주면 좋다고 한다.
//                throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + operator);
//
//        }
//        return new CalculationResponse(request.getNum1(),request.getNum2(), request.getOperator(), result);
//    }
//
//

@PostMapping("/calculate")
public CalculationResponse postMethodName(@RequestBody CalculationRequest request){
    double result = calucatorServ.calculate(request);

    // 받은 결과를 DTO로 포장하여 반환
    return new CalculationResponse(request.getNum1(), request.getNum2(), request.getOperator(), result);
}




}
