package com.macaca.housekeeper.event.listeners;

import com.macaca.housekeeper.Main;
import com.macaca.housekeeper.services.MessageEventService;
import com.macaca.housekeeper.services.StepService;
import enums.StepStages;
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

    @Autowired
    private MessageEventService messageEventService;

    @Autowired
    private StepService stepService;


    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        String message = messageCreateEvent.getMessage().getContent();
        if(message.equalsIgnoreCase("!hk status")){
            messageEventService.performStatusCommand(messageCreateEvent);
        } else if(message.toLowerCase().contains("prank") && messageCreateEvent.getMessage().isPrivate() && messageCreateEvent.getMessage().getAuthor().isBotOwner()) {
            messageEventService.performPrankCommand(messageCreateEvent);
            stepService.completed(StepStages.INITIATE);
        }




    }
}
