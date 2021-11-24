package user_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"user_service.dao"})
@EnableAutoConfiguration
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {

        SpringApplication.run(StartApplication.class);

    }
}
