package com.worldhub.guide.config;

import library.config.SecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
@Import(SecurityConfiguration.class)
@Configuration
public class ApplicationConfiguration {



}
