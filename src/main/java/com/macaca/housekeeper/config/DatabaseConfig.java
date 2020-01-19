package com.macaca.housekeeper.config;

import entities.Step;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackageClasses = {Step.class})
public class DatabaseConfig {


}
