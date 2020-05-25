package ru.itis.kpfu.bentos.discordbootbot.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.JStatsService;

@Component
@Profile("sock")
@AllArgsConstructor
public class JStats extends SockCommand {

    private final JStatsService jStatsService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public String execute(MessageDto message) {
        var messageText = message.getText().substring(1);
        var responseMessage = MessageDto.builder()
                .text(jStatsService.getPlayerStats(messageText.substring(messageText.indexOf(" ")), message))
                .room(message.getRoom())
                .from(message.getFrom())
                .build();
        return objectMapper.writeValueAsString(responseMessage);
    }

    @Override
    public Headers header() {
        return Headers.stats;
    }

    @Override
    public String description() {
        return "Get stats for R6Siege player <br> " +
                "Arguments: <nickname> <platform> <br>" +
                "<type> (gen or ran)";
    }
}
