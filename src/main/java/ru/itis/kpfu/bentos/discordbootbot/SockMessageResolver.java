package ru.itis.kpfu.bentos.discordbootbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.command.Command;
import ru.itis.kpfu.bentos.discordbootbot.command.SockCommand;
import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;

import java.util.HashMap;
import java.util.Map;

@Profile("sock")
@Component
public class SockMessageResolver {

    private final ApplicationContext context;
    private final Map<String, SockCommand> commandMap = new HashMap<>();

    public SockMessageResolver(ApplicationContext applicationContext) {
        this.context = applicationContext;
        initializeCommands();
    }

    public Map<String, SockCommand> getCommandMap() {
        return commandMap;
    }

    private void initializeCommands() {
        var commands = context.getBeansOfType(SockCommand.class).values();
        commands.forEach(this::addCommand);
    }

    private void addCommand(SockCommand command) {
        commandMap.put(command.header().name(), command);
    }

    public String executeCommand(MessageDto message) {

        var content = message.getText().split(" ");

        if ((content[0].substring(0, 1)).equals("!")) {
            var commandText = content[0].substring(1);
            SockCommand command = commandMap.get(commandText);
            if (command == null) {
                throw new IllegalArgumentException("Unknown header " + commandText);
            }
            return command.execute(message);
        } else return "";
    }


}
