package ru.itis.kpfu.bentos.discordbootbot.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.SockMessageResolver;
import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;

import java.awt.*;

@Component
@Profile("sock")
public class JHelpCommand extends SockCommand {

    private final ApplicationContext context;
    private final ObjectMapper objectMapper;


    public JHelpCommand(ApplicationContext context, ObjectMapper objectMapper) {
        this.context = context;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public String execute(MessageDto message) {

        var header = Headers.values();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Help");
        builder.setColor(new Color(0xA0A0A0));
        builder.setDescription("Commands: \n");

        for (Headers headers :header) {
            builder.appendDescription("<br>")
                    .appendDescription(headers.name() + ": " + context.getBean(SockMessageResolver.class)
                    .getCommandMap()
                    .get(headers.name())
                    .description() + "<br>");
        }

        var responseText = builder.build().getDescription();
        var response = MessageDto.builder()
                .text(responseText)
                .from(message.getFrom())
                .room(message.getRoom())
                .build();

        return objectMapper.writeValueAsString(response);
    }

    @Override
    public Headers header() {
        return Headers.help;
    }

    @Override
    public String description() {
        return "Show all commands";
    }
}
