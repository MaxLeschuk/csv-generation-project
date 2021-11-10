package csv_generation.controllers;

import csv_generation.generator.CsvGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateController {

    @Autowired
    private CsvGeneratorImpl csvGenerator;

    @PostMapping("/generate")
    public String generate(@RequestBody String[] columns) {
        return csvGenerator.generate(columns);

    }


}
