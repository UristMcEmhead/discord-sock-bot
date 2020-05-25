package ru.itis.kpfu.bentos.discordbootbot.command;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.events.GenericEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.discordbootbot.utils.ValidationUtils;

@Component
@Profile("dis")
public abstract class Command {

    @Getter
    @Setter
    private ValidationUtils validationUtils;

    public abstract void execute(GenericEvent event);

    public abstract Headers header();

    public enum Headers{
        lead,
        stats,
        makeFriend,
        purge,
        test,
        help
    }

    public abstract String description();
}
