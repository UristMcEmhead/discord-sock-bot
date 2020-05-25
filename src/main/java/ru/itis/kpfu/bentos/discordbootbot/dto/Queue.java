package ru.itis.kpfu.bentos.discordbootbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Profile;

import java.util.Random;

@Data
@Profile({"dis","sock"})
public class Queue {

    private Casual casual;
    private Ranked ranked;
    private Other other;

}
