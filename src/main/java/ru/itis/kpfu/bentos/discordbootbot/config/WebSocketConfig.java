package ru.itis.kpfu.bentos.discordbootbot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.kpfu.bentos.discordbootbot.handlers.AuthHandshakeHandler;
import ru.itis.kpfu.bentos.discordbootbot.handlers.WebSocketMessagesHandler;

@Configuration
@Profile("sock")
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketMessagesHandler handler;
    private final AuthHandshakeHandler handshakeHandler;

    public WebSocketConfig(WebSocketMessagesHandler handler, AuthHandshakeHandler handshakeHandler) {
        this.handler = handler;
        this.handshakeHandler = handshakeHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/chat")
                .setHandshakeHandler(handshakeHandler)
                .withSockJS();
    }
}
