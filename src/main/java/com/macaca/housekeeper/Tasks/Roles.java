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
        Main.api.getRolesByNameIgnoreCase(roleToAdd).forEach(role -> user.addRole(role));
    }
}
