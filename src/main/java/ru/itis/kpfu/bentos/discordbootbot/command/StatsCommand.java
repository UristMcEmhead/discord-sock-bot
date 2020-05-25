package ru.itis.kpfu.bentos.discordbootbot.command;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.service.interfaces.StatsService;
import ru.itis.kpfu.bentos.discordbootbot.utils.ValidationUtils;

@Component
@AllArgsConstructor
@Profile("dis")
public class StatsCommand extends Command {

    private final ValidationUtils validationUtils;
    private final StatsService statsService;

    @Override
    public void execute(GenericEvent event) {
        var messageReceivedEvent = (MessageReceivedEvent) event;
        try {
            validationUtils.validate(messageReceivedEvent.getMessage().getContentRaw(), 5);
        } catch (IllegalArgumentException e){
            messageReceivedEvent.getChannel().sendMessage("Can't find command " + this.header().name() + "with allowed arguments").queue();
        }
        statsService.getPlayerStats((MessageReceivedEvent) event);
    }

    @Override
    public Headers header() {
        return Headers.stats;
    }

    @Override
    public String description() {
        return "Get stats for R6Siege player \n " +
                "Arguments: <nickname> <platform> \n " +
                "<type> (gen or ran)";
    }
}
