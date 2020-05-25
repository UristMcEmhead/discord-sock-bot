package ru.itis.kpfu.bentos.discordbootbot.command;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.service.interfaces.LeadService;
import ru.itis.kpfu.bentos.discordbootbot.utils.ValidationUtils;

import javax.validation.Validator;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Profile("dis")
public class LeadCommand extends Command {

    private final LeadService leadService;
    private final ValidationUtils validationUtils;

    @Override
    public void execute(GenericEvent event) {

        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;

        try {
            validationUtils.validate(messageReceivedEvent.getMessage().getContentRaw(), 2);
        } catch (IllegalArgumentException e) {
            messageReceivedEvent.getChannel().sendMessage("Can't find command " + this.header().name() + "with allowed arguments").queue();
        }

        var bots = messageReceivedEvent.getGuild().getMembers()
                .stream()
                .filter((x) -> x.getUser().isBot())
                .filter((x) -> x.getOnlineStatus().equals(OnlineStatus.ONLINE))
                .collect(Collectors.toList());
        var permissionIsAdmin = messageReceivedEvent.getGuild().getMember(messageReceivedEvent.getAuthor()).hasPermission(Permission.ADMINISTRATOR);

        leadService.nextHop(permissionIsAdmin, bots, messageReceivedEvent);
    }

    @Override
    public Headers header() {
        return Headers.lead;
    }

    @Override
    public String description() {
        return "Selects the next bot to drive in the catch-up \n Doesn't have arguments";
    }
}
