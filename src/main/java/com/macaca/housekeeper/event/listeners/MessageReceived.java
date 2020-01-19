package com.macaca.housekeeper.event.listeners;

import com.macaca.housekeeper.Main;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Date;

@Component
public class MessageReceived implements MessageCreateListener {

    @Autowired
    private DiscordApi api;

    public static long startupTime;


    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        String message = messageCreateEvent.getMessage().getContent();
        String owner = messageCreateEvent.getApi().getOwner().join().getName();
        long seconds = (new Date().getTime() - startupTime) / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds";
        if(message.equalsIgnoreCase("!hk status")){
            if(messageCreateEvent.getMessage().getAuthor().isBotOwner()){
                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setTitle(api.getYourself().getName())
                                .setDescription("Bot Details")
                                .addField("Owner", owner)
                                .addField("Server count", "" + api.getServers().size())
                                .addField("Uptime", time)
                                .addField("Invite link", api.createBotInvite())
                                .setColor(Color.RED))
                        .send(messageCreateEvent.getChannel());
            }
        }

        messageCreateEvent.getMessage().getAuthor().asUser().ifPresent(user -> {
            System.out.println("Wrong answer!");
        });
    }
}
