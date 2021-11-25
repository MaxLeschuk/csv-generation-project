package com.epam.training.userservice.config;

import org.jobrunr.configuration.JobRunr;

import org.jobrunr.configuration.JobRunrConfiguration;
import org.jobrunr.server.JobActivator;
import org.jobrunr.storage.sql.common.SqlStorageProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Beans {
    @Autowired
    @Bean
    public JobRunrConfiguration.JobRunrConfigurationResult initJobRunr(DataSource dataSource, JobActivator jobActivator) {
        return JobRunr.configure()
                .useJobActivator(jobActivator)
                .useStorageProvider(SqlStorageProviderFactory
                        .using(dataSource))
                .useBackgroundJobServer()
                .useDashboard()
                .initialize();
    }
}
