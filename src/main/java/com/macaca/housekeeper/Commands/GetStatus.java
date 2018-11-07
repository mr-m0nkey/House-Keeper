package com.macaca.housekeeper.Commands;

import com.macaca.housekeeper.Main;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.Date;

public class GetStatus implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        String message = messageCreateEvent.getMessage().getContent();
        String owner = messageCreateEvent.getApi().getOwner().join().getName();
        long seconds = (new Date().getTime() - Main.startupTime) / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds";
        if(message.equalsIgnoreCase("!hk status")){
            if(messageCreateEvent.getMessage().getAuthor().isBotOwner()){
                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setTitle(Main.api.getYourself().getName())
                                .setDescription("Bot Details")
                                .addField("Owner", owner)
                                .addField("Server count", "" + Main.api.getServers().size())
                                .addField("Uptime", time)
                                .addField("Invite link", Main.api.createBotInvite())
                                .setColor(Color.RED))
                        .send(messageCreateEvent.getChannel());
            }
        }

    }
}
