package ru.itis.kpfu.bentos.discordbootbot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.MessageResolver;
import ru.itis.kpfu.bentos.discordbootbot.utils.ValidationUtils;

import java.awt.*;

@Component
@Profile("dis")
public class HelpCommand extends Command {

    private final ApplicationContext context;
    private final ValidationUtils validationUtils;


    public HelpCommand(ApplicationContext context, ValidationUtils validationUtils) {
        this.context = context;
        this.validationUtils = validationUtils;
    }

    @Override
    public void execute(GenericEvent event) {
        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;

        try {
            validationUtils.validate(messageReceivedEvent.getMessage().getContentRaw(), 2);
        } catch (IllegalArgumentException e){
            messageReceivedEvent.getChannel().sendMessage("Can't find command " + this.header().name() + "with allowed arguments").queue();
        }

        var header = Headers.values();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Help");
        builder.setColor(new Color(0xA0A0A0));
        builder.setDescription("Commands: \n");

        for (Headers headers : header) {
            builder.addField(headers.name(), context.getBean(MessageResolver.class).getCommandMap().get(headers.name()).description(), false);
        }

        messageReceivedEvent.getChannel().sendMessage(builder.build()).queue();
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
