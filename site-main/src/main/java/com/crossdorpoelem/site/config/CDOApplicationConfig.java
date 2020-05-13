package com.crossdorpoelem.site.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.crossdorpoelem.query", "com.crossdorpoelem.command" })
public class CDOApplicationConfig {
}
