package com.macaca.housekeeper.event.listeners;

import com.macaca.housekeeper.Main;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import com.macaca.housekeeper.event.listeners.MessageReceived;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.util.Date;

@Component
public class ApplicationStart  {



    @Autowired
    private DiscordApi api;

    @Autowired
    private MessageReceived messageReceived;


    public static long startupTime;





    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        setupBot();
        setupListeners();
    }




    private void setupListeners(){
        api.addMessageCreateListener(messageReceived);
        api.addServerMemberLeaveListener(serverMemberLeaveEvent -> {
            // TODO: 01/08/2018 set main channel
            serverMemberLeaveEvent.getUser().sendMessage("Sayounara");
        });
    }

    private void setupBot(){
        System.out.println("Invite me! " + api.createBotInvite());
        api.updateActivity(ActivityType.WATCHING, "paint dry");
        if (api.getYourself().hasDefaultAvatar()){
            api.updateAvatar(new File("files/profile.jpeg"));
        }
        api.getCachedUserByNameAndDiscriminator("mr_m0nkey_", "8865").ifPresent(user -> {
            user.sendMessage("Hello ma'am");
        });
    }
}
