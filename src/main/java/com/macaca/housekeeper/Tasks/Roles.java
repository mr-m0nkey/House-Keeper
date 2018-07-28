package com.macaca.housekeeper.Tasks;

import com.macaca.housekeeper.Main;
import com.macaca.housekeeper.Utils.CommandUtils;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collection;
import java.util.Optional;

public abstract class Roles {

    public static void addRole(MessageCreateEvent event){
        String roleToAdd = CommandUtils.getParam(event.getMessage().getContent());
        System.out.println("adding role:" + roleToAdd);
        User user = event.getMessage().getUserAuthor().get();
        if(Main.api.getRolesByNameIgnoreCase(roleToAdd).isEmpty()){
            event.getChannel().sendMessage(roleToAdd + " isn't a role on this server");
            return;
        }
        Main.api.getRolesByNameIgnoreCase(roleToAdd).forEach(role -> user.addRole(role));
    }

    public static void removeRole (MessageCreateEvent event){
        String roleToRemove = CommandUtils.getParam(event.getMessage().getContent());
        System.out.println("removing role:" + roleToRemove);
        User user = event.getMessage().getUserAuthor().get();
        if(Main.api.getRolesByNameIgnoreCase(roleToRemove).isEmpty()){
            event.getChannel().sendMessage(roleToRemove + " isn't a role on this server");
            return;
        }
        Main.api.getRolesByNameIgnoreCase(roleToRemove).forEach(role -> user.removeRole(role));
    }

    // TODO: 28/07/2018 improve readability 
    public static void setReactionMessage(MessageCreateEvent event){
        String messageId = CommandUtils.getParam(event.getMessage().getContent());
        Message message = event.getChannel().getMessageById(messageId).join();
        Main.api.getRoles().stream().filter(role -> {
            return !role.isEveryoneRole();
        }).forEach(role -> {
            Message roleReactionMessage = event.getChannel().sendMessage("React to set a reaction role for " + role.getName()).join();
            roleReactionMessage.addReactionAddListener(roleReactionMessageReactionAddEvent -> {
               message.addReactionAddListener(messageReactionAddEvent -> {
                  if(roleReactionMessageReactionAddEvent.getEmoji().equalsEmoji(messageReactionAddEvent.getEmoji())){
                      messageReactionAddEvent.getUser().addRole(role);
                      roleReactionMessage.delete();
                  }
               });
               message.addReactionRemoveListener(reactionRemoveEvent -> {
                   if(roleReactionMessageReactionAddEvent.getEmoji().equalsEmoji(reactionRemoveEvent.getEmoji())){
                       reactionRemoveEvent.getUser().removeRole(role);
                   }
               });
            });
        });
        message.addReactionAddListener(reactionAddEvent -> {

        });
    }
}
