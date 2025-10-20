package com.pgc.excrud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication
@MapperScan("com.pgc.excrud.mapper")
public class Day1020Application {

    public static void main(String[] args) {
        SpringApplication.run(Day1020Application.class, args);
    }

}
