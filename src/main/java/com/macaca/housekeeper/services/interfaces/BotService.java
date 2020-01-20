package com.macaca.housekeeper.services.interfaces;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.user.User;

import java.util.concurrent.ExecutionException;

public interface BotService {

    void sendMessageToOwner(MessageBuilder messageBuilder) throws ExecutionException, InterruptedException;

    void sendMessageToUser(User user, MessageBuilder messageBuilder);

    void sendMessageToChannel(TextChannel channel, MessageBuilder messageBuilder);
}
