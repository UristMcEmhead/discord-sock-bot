package ru.itis.kpfu.bentos.discordbootbot.command;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.events.GenericEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;
import ru.itis.kpfu.bentos.discordbootbot.utils.ValidationUtils;

@Component
@Profile("sock")
public abstract class SockCommand {

    @Getter
    @Setter
    private ValidationUtils validationUtils;

    public abstract String execute(MessageDto message);

    public abstract Headers header();

    public enum Headers{
        stats,
        friend,
        help,
        approve
    }

    public abstract String description();

}
