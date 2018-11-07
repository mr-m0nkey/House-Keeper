package com.macaca.housekeeper;

import com.macaca.housekeeper.Commands.GetStatus;
import com.macaca.housekeeper.Utils.CommandUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.message.MessageSet;

import java.io.File;
import java.util.Date;

public class Main {

    public static  DiscordApi api;

    public static long startupTime;

    public static void main(String[] args) {

        //gets the token from the .env
        Dotenv dotenv = Dotenv.configure().directory("./").load();
        String token = dotenv.get("TOKEN");

        //logs in
        api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Logged in!");
        startupTime = new Date().getTime();


        setupBot();
        setupListeners();



    }

    private static void setupListeners(){
        api.addMessageCreateListener(new GetStatus());
        api.addServerMemberLeaveListener(serverMemberLeaveEvent -> {
            // TODO: 01/08/2018 set main channel
            serverMemberLeaveEvent.getServer().getChannelsByNameIgnoreCase("general").get(0).asServerTextChannel().get()
                    .sendMessage("Sayounara " + serverMemberLeaveEvent.getUser().getName());
        });
    }

    private static void setupBot(){
        api.updateActivity(ActivityType.WATCHING, "paint dry");
        if (api.getYourself().hasDefaultAvatar()){
            api.updateAvatar(new File("files/profile.jpeg"));
        }
    }
}
