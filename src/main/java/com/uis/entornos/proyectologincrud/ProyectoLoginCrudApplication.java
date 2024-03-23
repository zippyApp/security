package com.uis.entornos.proyectologincrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProyectoLoginCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoLoginCrudApplication.class, args);
    }

}
