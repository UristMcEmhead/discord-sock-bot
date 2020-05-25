package ru.itis.kpfu.bentos.discordbootbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Profile;

@Data
@Profile({"dis","sock"})
public class R6Player {

    private String username;
    private String platform;
    private String ubisoft_id;
    private Progression progression;
    private Stats[] stats;

}
