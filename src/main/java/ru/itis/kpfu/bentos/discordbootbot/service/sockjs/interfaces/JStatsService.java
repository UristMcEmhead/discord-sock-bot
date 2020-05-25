package ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces;

import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;

public interface JStatsService {

    String getPlayerStats(String command, MessageDto messageDto);

}
