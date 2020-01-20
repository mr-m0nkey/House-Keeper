package com.macaca.housekeeper.services;

import com.macaca.housekeeper.services.interfaces.BotService;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class BotServiceImpl implements BotService {

    @Autowired
    private DiscordApi api;

    @Override
    public void sendMessageToOwner(MessageBuilder messageBuilder) throws ExecutionException, InterruptedException {
        messageBuilder.send(api.getOwner().get());
    }

    @Override
    public void sendMessageToUser(User user, MessageBuilder messageBuilder) {
        messageBuilder.send(user);
    }

    @Override
    public void sendMessageToChannel(TextChannel channel, MessageBuilder messageBuilder) {
        messageBuilder.send(channel);
    }


}
