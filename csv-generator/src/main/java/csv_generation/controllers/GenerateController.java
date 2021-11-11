package csv_generation.controllers;

import csv_generation.service.CsvServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateController {

    @Autowired
    private CsvServiceImpl csvService;

    @PostMapping("/generate")
    public String generate(@RequestBody String[] columns) {
        return csvService.create(columns);

    }


}
