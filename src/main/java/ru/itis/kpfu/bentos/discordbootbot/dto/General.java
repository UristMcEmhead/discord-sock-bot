package ru.itis.kpfu.bentos.discordbootbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Profile;

@Data
@Profile({"dis","sock"})
public class General {

    private double wl;
    private double kd;
    private int games_played;


}
