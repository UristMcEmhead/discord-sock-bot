package ru.itis.kpfu.bentos.discordbootbot.command;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.utils.ValidationUtils;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@AllArgsConstructor
@Profile("dis")
public class PurgeCommand extends Command {

    private final ValidationUtils validationUtils;

    @Override
    public void execute(GenericEvent event) {

        var messageReceivedEvent = (MessageReceivedEvent) event;
        try {
            validationUtils.validate(messageReceivedEvent.getMessage().getContentRaw(), 2);
        } catch (IllegalArgumentException e){
            messageReceivedEvent.getChannel().sendMessage("Can't find command " + this.header().name() + "with allowed arguments").queue();
        }

        new Thread(() ->
        {
            while (true) {

                var channel = messageReceivedEvent.getTextChannel();

                List<Message> messages = channel.getHistory().retrievePast(90).complete();
                messages.removeIf(m -> m.getTimeCreated().isBefore(OffsetDateTime.now().minus(2, ChronoUnit.WEEKS)));

                if (messages.isEmpty()) {
                    System.out.println("Done deleting: " + channel);
                    return;
                }

                channel.deleteMessages(messages).complete();
            }
        }).start();
    }

    @Override
    public Headers header() {
        return Headers.purge;
    }

    @Override
    public String description() {
        return "Delete all messages from channel \n doesn't have arguments";
    }

}
