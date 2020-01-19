package com.macaca.housekeeper;


import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Main {


    public static long startupTime;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        startupTime = new Date().getTime();
    }


    @Bean
    DiscordApi getApi() {
        Dotenv dotenv = Dotenv.configure().directory("./").load();
        String token = dotenv.get("TOKEN");

        //logs in
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Logged in!");
        api.getOwner().join().sendMessage("I'm awake");
        return api;
    }
}
