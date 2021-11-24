package com.epam.training.userservice.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvTemplate {

    private Integer id;
    private String[] columns;

}
