package com.sasha.autobuysale;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoBuySaleApplication {

    public static void main(String[] args) {
        loadItemsFromEnvFile();
        SpringApplication.run(AutoBuySaleApplication.class, args);
    }

    private static void loadItemsFromEnvFile() {

        Dotenv dotenv = Dotenv
                .configure()
                .ignoreIfMissing()
                .load();

        dotenv.entries()
                .forEach(item -> System.setProperty(item.getKey(), item.getValue()));
    }
}
