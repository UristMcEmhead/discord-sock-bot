package ru.itis.kpfu.bentos.discordbootbot.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.kpfu.bentos.discordbootbot.SockMessageResolver;
import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.JStatsService;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.JsonProcessService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@EnableWebSocket
@AllArgsConstructor
@Profile("sock")
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();
    private final JsonProcessService jsonProcessService;
    private final SockMessageResolver messageResolver;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void handleMessage(@NotNull WebSocketSession session, WebSocketMessage<?> message) {

        String messageText = (String) message.getPayload();
        var messageFromJson = jsonProcessService.parse(messageText);

        String temp = messageResolver.executeCommand(messageFromJson);
        TextMessage response = new TextMessage(temp);

        if (!sessions.containsKey(Objects.requireNonNull(messageFromJson).getFrom())) {
            sessions.put(messageFromJson.getFrom().getName(), session);
        }

        for (WebSocketSession currentSession : sessions.values()) {
            try {
                currentSession.sendMessage(message);
                currentSession.sendMessage(response);
            } catch (IOException e) {
                System.out.println("Can't send message to session: " + session.getUri() + "\n" + e.getMessage());
            }
        }
    }

}
