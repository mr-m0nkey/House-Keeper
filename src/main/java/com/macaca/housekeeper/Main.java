package com.macaca.housekeeper;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {
    public static void main(String[] args) {
        String token = "NDcwNTc0OTcyMDU4OTkyNjUw.DjYs3g.bXWKBy0aw5tqo57_sYpEmnSJe0g";
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Logged in!");
    }
}
