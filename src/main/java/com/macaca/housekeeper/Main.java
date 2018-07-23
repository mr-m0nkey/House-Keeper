package com.macaca.housekeeper;

import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().directory("./").load();

        String token = dotenv.get("TOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Logged in!");

        api.addMessageCreateListener(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
}
