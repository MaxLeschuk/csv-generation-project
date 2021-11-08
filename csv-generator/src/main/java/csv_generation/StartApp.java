package csv_generation;


import csv_generation.service.CsvServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StartApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StartApp.class);
        CsvServiceImpl csvService = applicationContext.getBean(CsvServiceImpl.class);

    }
}
