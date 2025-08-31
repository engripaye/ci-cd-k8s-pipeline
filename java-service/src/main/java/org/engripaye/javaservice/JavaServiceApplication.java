package org.engripaye.javaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavaServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(JavaServiceApplication.class, args);
    }

    @GetMapping("/")
    public String root(){
        return "{\"status\":\"ok\",\"service\":\"java-service\"}";
    }
}
