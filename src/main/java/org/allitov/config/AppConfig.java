package org.allitov.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"org.allitov.config", "org.allitov.beans"})
@PropertySource("classpath:application.properties")
public class AppConfig {

}
