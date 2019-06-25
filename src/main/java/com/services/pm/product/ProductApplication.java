package com.services.pm.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        /* ConfigurableApplicationContext context = new SpringApplicationBuilder(ProductApplication.class)
                .logStartupInfo(false)
                .build(args)
                .run();

        context.close();
        */

        SpringApplication.run(ProductApplication.class, args);
    }
}
