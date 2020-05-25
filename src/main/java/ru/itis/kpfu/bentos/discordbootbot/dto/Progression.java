package ru.itis.kpfu.bentos.discordbootbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Profile;

@Data
@Profile({"dis","sock"})
public class Progression {

    private int level;
    private int lootbox_probability;
    private int total_xp;



}
