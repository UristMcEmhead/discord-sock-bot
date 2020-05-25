package ru.itis.kpfu.bentos.discordbootbot.controller;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/test")
public class TestEndpoint {

    @OnMessage
    public String handleTextMessage(String message) {
        System.out.println("New Text Message Received");
        return message;
    }

}
