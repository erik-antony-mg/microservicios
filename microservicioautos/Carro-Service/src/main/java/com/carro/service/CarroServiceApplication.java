package com.carro.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.config.client.ConfigServerInstanceProvider;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarroServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CarroServiceApplication.class, args);
    }

}
