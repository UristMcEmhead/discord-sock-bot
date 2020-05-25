package ru.itis.kpfu.bentos.discordbootbot.service.interfaces;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface StatsService {

    void getPlayerStats(MessageReceivedEvent event);

}
