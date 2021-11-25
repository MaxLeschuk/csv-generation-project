package com.epam.training.csvgeneration.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Autowired
    private ConfigProperties configProperties;

    @Bean
    public AmazonS3 amazonS3() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");

        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:9000", Regions.US_EAST_1.name()))
                .withPathStyleAccessEnabled(true)

                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(configProperties.getS3AccessKey(),
                                                configProperties.getS3SecretKey())))
                .withClientConfiguration(clientConfiguration)
                .build();
    }

}
