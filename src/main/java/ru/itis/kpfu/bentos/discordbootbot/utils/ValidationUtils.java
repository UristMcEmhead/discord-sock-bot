package ru.itis.kpfu.bentos.discordbootbot.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.command.Command;

@Component
@Profile("dis")
public class ValidationUtils {

    public void validate(String content, int length) throws IllegalArgumentException {
        if (content.split(" ").length != length) {
            throw new IllegalArgumentException("Many or less arguments found for command ");
        }
    }
}
