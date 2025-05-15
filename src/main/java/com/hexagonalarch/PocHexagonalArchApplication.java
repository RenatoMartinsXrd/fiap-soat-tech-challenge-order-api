package com.hexagonalarch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hexagonalarch"})
public class PocHexagonalArchApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocHexagonalArchApplication.class, args);
    }

}
