package com.macaca.housekeeper.Utils;

import com.macaca.housekeeper.Main;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

import java.util.Optional;

public abstract class CommandUtils {


    public static boolean isCommand(String text){
        return text.split(" ")[0].equalsIgnoreCase("#bot");
    }

    public static String getMethod(String text){
        return text.split(" ")[1];
    }

    public static String getParam(String text){
        String[] words = text.split(" ");
        String param = "";
        for(int i = 2; i < words.length; i++){
            param += words[i] + " ";
        }
        System.out.println(words[2]);
        return param.trim().toLowerCase();
    }

}
