package com.epam.training.csvgeneration.resource;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import com.epam.training.csvgeneration.exceptions.TechnicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.UUID;
import java.util.logging.Logger;

@Component
class StorageServiceImpl implements StorageService {


    private Logger logger = Logger.getLogger(StorageServiceImpl.class.getName());

    @Autowired
    private MinioService minioService;


    @Override
    public String save(InputStream inputStream) {
        try {
            String path = UUID.randomUUID().toString();
            minioService.upload(Path.of(path), inputStream, "text/csv");
            return path;
        } catch (MinioException e) {
            throw new TechnicalException(e.getLocalizedMessage());
        }

    }
}
