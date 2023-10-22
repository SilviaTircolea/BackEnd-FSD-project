package com.twitter.app.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfiguration {

    private static Logger LOG = LoggerFactory.getLogger(ApplicationConfiguration.class);

    private final Environment environment;
    private final ResourceLoader resourceLoader;

    @Autowired
    public ApplicationConfiguration(Environment environment,
                                    ResourceLoader resourceLoader) {
        this.environment = environment;
        this.resourceLoader = resourceLoader;
    }
}
