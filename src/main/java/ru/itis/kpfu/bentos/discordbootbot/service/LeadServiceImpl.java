package ru.itis.kpfu.bentos.discordbootbot.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.discordbootbot.service.interfaces.EmojiService;
import ru.itis.kpfu.bentos.discordbootbot.service.interfaces.LeadService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Profile("dis")
public class LeadServiceImpl implements LeadService {

    private final JDA jda;
    private final EmojiService emojiService;

    @Override
    public void nextHop(boolean isAdmin, List<Member> memberList, MessageReceivedEvent event) {

        for (int i = 0; i < memberList.size(); ++i) {
            if (memberList.get(i).getId().equals(jda.getSelfUser().getId())) memberList.remove(memberList.get(i));
        }

        MessageChannel channel = event.getChannel();
        var nextHopId = (memberList.get((new Random()).nextInt(memberList.size())).getId());

        if ((event.getAuthor().isBot() || isAdmin || event.getAuthor().getName().equals("Bentos"))) {
            channel.sendMessage("lead "
                    + jda.getUserById(nextHopId).getAsMention()
                    + emojiService.getRandomEmoji().getUnicode()
            ).queue();
        }
    }
}
