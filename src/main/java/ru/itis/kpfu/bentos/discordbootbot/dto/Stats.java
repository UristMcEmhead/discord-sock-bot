package ru.itis.kpfu.bentos.discordbootbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Profile;

@Data
@Profile({"dis","sock"})
public class Stats {

    private General general;
    private Queue queue;

}
