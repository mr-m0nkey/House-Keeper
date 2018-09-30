package com.macaca.housekeeper.Commands;

import com.macaca.housekeeper.Main;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class GetStatus implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        String message = messageCreateEvent.getMessage().getContent();
        String owner = messageCreateEvent.getApi().getOwner().join().getName();
        if(message.equalsIgnoreCase("!hk status")){
            if(messageCreateEvent.getMessage().getAuthor().isBotOwner()){
                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setTitle(Main.api.getYourself().getName())
                                .setDescription("Bot Details")
                                .addField("Owner", owner)
                                .addField("Server count", "" + Main.api.getServers().size())
                                .setColor(Color.RED))
                        .send(messageCreateEvent.getChannel());
            }
        }

    }
}
