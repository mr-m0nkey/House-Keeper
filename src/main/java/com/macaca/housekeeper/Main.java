package com.macaca.housekeeper;

import com.macaca.housekeeper.Commands.GetStatus;
import com.macaca.housekeeper.Utils.CommandUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageSet;

public class Main {

    public static  DiscordApi api;

    public static void main(String[] args) {

        //gets the token from the .env
        Dotenv dotenv = Dotenv.configure().directory("./").load();
        String token = dotenv.get("TOKEN");

        //logs in
        api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Logged in!");

        setupCommands();
        setupListeners();


    }

    private static void setupCommands(){
        api.addMessageCreateListener(new GetStatus());
    }

    private static void setupListeners(){
        api.addServerMemberLeaveListener(serverMemberLeaveEvent -> {
            // TODO: 01/08/2018 set main channel
            serverMemberLeaveEvent.getServer().getChannelsByNameIgnoreCase("general").get(0).asServerTextChannel().get()
                    .sendMessage("Sayounara " + serverMemberLeaveEvent.getUser().getName());
        });
    }
}
