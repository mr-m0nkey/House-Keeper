package com.macaca.housekeeper.event.listeners;

import com.macaca.housekeeper.services.MessageEventService;
import com.macaca.housekeeper.services.StepService;
import enums.StepStages;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageReceived implements MessageCreateListener {

    @Autowired
    private MessageEventService messageEventService;

    @Autowired
    private StepService stepService;


    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        String message = messageCreateEvent.getMessage().getContent();
        if(message.equalsIgnoreCase("!hk status")) {
            messageEventService.performStatusCommand(messageCreateEvent);
        } else if(message.toLowerCase().contains("prank") && messageCreateEvent.getMessage().isPrivate() && messageCreateEvent.getMessage().getAuthor().isBotOwner()) {
            messageEventService.performPrankCommand(messageCreateEvent);
            stepService.completed(StepStages.INITIATE);
        }




    }
}
