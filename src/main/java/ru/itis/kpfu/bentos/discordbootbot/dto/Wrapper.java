package ru.itis.kpfu.bentos.discordbootbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Profile;

@Data
@Profile({"dis","sock"})
public class Wrapper {

    private String ubisoft_id;

}
