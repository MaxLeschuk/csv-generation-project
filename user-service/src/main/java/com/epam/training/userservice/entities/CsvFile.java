package com.epam.training.userservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CsvFile {
    private Integer id;
    private String path;
    private String userId;

    public CsvFile(String userId, String path) {
        this.path = path;
        this.userId = userId;
    }
}
