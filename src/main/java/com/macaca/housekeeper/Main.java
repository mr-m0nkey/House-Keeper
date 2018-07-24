package com.macaca.housekeeper;

import com.macaca.housekeeper.Tasks.Roles;
import com.macaca.housekeeper.Utils.CommandUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static  DiscordApi api;

    public static void main(String[] args) {

        //gets the token from the .env
        Dotenv dotenv = Dotenv.configure().directory("./").load();
        String token = dotenv.get("TOKEN");

        //logs in
        api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Logged in!");


        api.addMessageCreateListener(event -> {
            String message = event.getMessage().getContent();
            System.out.println("You sent a message: " + message);
            if(CommandUtils.isCommand(message)){
                System.out.println("The message is a command");
                switch (CommandUtils.getMethod(message)){
                    case "addrole":
                        Roles.addRole(event);
                        break;
                    default:
                        System.out.println(CommandUtils.getMethod(message));
                        break;
                }
            }
        });


    }
}
