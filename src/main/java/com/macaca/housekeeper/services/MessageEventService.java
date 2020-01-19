package com.macaca.housekeeper.services;

import com.macaca.housekeeper.Main;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Date;

@Component
public class MessageEventService {

    @Autowired
    private DiscordApi api;


    public void performStatusCommand(MessageCreateEvent messageCreateEvent) {
        String message = messageCreateEvent.getMessage().getContent();
        String owner = messageCreateEvent.getApi().getOwner().join().getName();
        long seconds = (new Date().getTime() - Main.startupTime) / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds";
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

    public void performPrankCommand(MessageCreateEvent messageCreateEvent) {
        String victim = messageCreateEvent.getMessage().getContent().split(" ")[1]; //TODO refactor this is terrible
        api.getCachedUserByNameAndDiscriminator(victim.split("#")[0], victim.split("#")[1]).ifPresent(user -> {
            user.sendMessage("Hello ma'am");
        });
        //TODO save pranked user's id in the database
    }
}
