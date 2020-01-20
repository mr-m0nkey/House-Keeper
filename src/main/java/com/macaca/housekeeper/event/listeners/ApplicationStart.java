package com.macaca.housekeeper.event.listeners;


import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ApplicationStart  {



    @Autowired
    private DiscordApi api;

    @Autowired
    private MessageReceived messageReceived;


    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        setupBot();
        setupListeners();
    }




    private void setupListeners(){
        api.addMessageCreateListener(messageReceived);
        api.addReconnectListener(reconnectEvent -> {
            api.getOwner().thenAcceptAsync(owner -> {
                owner.sendMessage("Reconnected");
            });
        });
        api.addServerMemberLeaveListener(serverMemberLeaveEvent ->
            // TODO: 01/08/2018 set main channel
            serverMemberLeaveEvent.getUser().sendMessage("Sayounara")
        );
    }

    private void setupBot(){
        System.out.println("Invite me! " + api.createBotInvite());
        api.updateActivity(ActivityType.WATCHING, "paint dry");
        if (api.getYourself().hasDefaultAvatar()){
            api.updateAvatar(new File("files/profile.jpeg"));
        }
    }
}
