package com.epam.training.csvgeneration.resource;

import com.amazonaws.services.s3.AmazonS3;
import com.epam.training.csvgeneration.config.ConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Logger;

@Slf4j
@Component
class S3StorageServiceImpl implements StorageService {

    @Autowired
    private ConfigProperties configProperties;

    private Logger logger = Logger.getLogger(S3StorageServiceImpl.class.getName());

    @Autowired
    private AmazonS3 s3client;


    @Override
    public String save(InputStream inputStream) {
            String path = UUID.randomUUID().toString();
            log.info("s11111111111111");
            s3client.putObject(configProperties.getS3Bucket(),path,inputStream,null);
            log.info(path + " was saved.");
            return path;

    }
}
