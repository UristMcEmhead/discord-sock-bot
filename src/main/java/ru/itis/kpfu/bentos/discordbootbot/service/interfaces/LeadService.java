package ru.itis.kpfu.bentos.discordbootbot.service.interfaces;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface LeadService {

    void nextHop(boolean isAdmin, List<Member> memberList, MessageReceivedEvent event);

}
