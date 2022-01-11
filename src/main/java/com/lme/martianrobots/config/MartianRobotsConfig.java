package com.lme.martianrobots.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Scanner;

@Configuration
public class MartianRobotsConfig {

    @Value("classpath:input.txt")
    private Resource inputCommands;

    @Bean
    public Scanner scanner() throws IOException {
        return new Scanner(inputCommands.getFile());
    }

}
