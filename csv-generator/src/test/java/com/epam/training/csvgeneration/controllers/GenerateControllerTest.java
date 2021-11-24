package com.epam.training.csvgeneration.controllers;

import com.epam.training.csvgeneration.service.CsvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class GenerateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CsvService csvService;

    @InjectMocks
    private GenerateController generateController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(generateController).build();
    }

    @Test
    void test_generate() throws Exception {
        given(csvService.create(new String[]{"col1","col2","col3"})).willReturn("path");
        this.mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(
                                "[\"col1\",\"col2\",\"col3\"]"))
                .andExpect(status().isOk())
                .andExpect(content().string("path"));
    }
}