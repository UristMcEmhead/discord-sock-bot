package ru.itis.kpfu.bentos.discordbootbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.command.Command;

import java.util.HashMap;
import java.util.Map;

@Component
@Profile("dis")
public class MessageResolver {

    private final ApplicationContext context;

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    private final Map<String, Command> commandMap = new HashMap<>();
    private final JDA jda;

    public MessageResolver(ApplicationContext applicationContext, JDA jda) {
        this.context = applicationContext;
        this.jda = jda;
        initializeCommands();
    }

    private void initializeCommands() {
        var commands = context.getBeansOfType(Command.class).values();
        commands.forEach(this::addCommand);
    }

    private void addCommand(Command command) {
        commandMap.put(command.header().name(), command);
    }

    public void executeCommand(MessageReceivedEvent event) {

        Message message = event.getMessage();
        String[] content = message.getContentRaw().split(" ");
        var mention = content[content.length - 1];

        var commandText = event.getMessage().getContentRaw().split(" ")[0];
        Command command = commandMap.get(commandText);

        if (mention.equals(jda.getSelfUser().getAsMention()) || mention.equals("<@!708714896933519401>")) {
            if (command == null) {
                throw new IllegalArgumentException("Unknown header " + commandText);
            }
            command.execute(event);
        }
    }
}
