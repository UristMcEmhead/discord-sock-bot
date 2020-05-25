package ru.itis.kpfu.bentos.discordbootbot.service.sockjs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;
import ru.itis.kpfu.bentos.discordbootbot.dto.R6Player;
import ru.itis.kpfu.bentos.discordbootbot.service.interfaces.R6ApiService;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.CookieService;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.FriendService;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.JStatsService;

@Service
@AllArgsConstructor
@Profile("sock")
public class JStatsServiceImpl implements JStatsService {

    private final R6ApiService r6ApiService;
    private final ObjectMapper objectMapper;
    private final FriendService friendService;

    @Override
    public String getPlayerStats(String command, MessageDto messageDto) {
        var message = command.split(" ");
        var username = message[1];
        var platform = message[2];
        var type = message[3];
        if(!friendService.isApproved(username, messageDto.getFrom().getName())){
            return "You are not friends with user " + username + "<br> So you have to become friends";
        }
        try {
            var user = r6ApiService.getUser(username, platform);
            R6Player player = objectMapper.readValue(user, R6Player.class);
            if (type.equals("gen"))
                return sendGeneralMessage(player);
            else if (type.equals("ran")) return sendRankedMessage(player);
            else return "Illegal request parameter found at position 3";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String sendGeneralMessage(R6Player player) {

        var general = player.getStats()[0].getGeneral();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setDescription("Статистика" + "<br>" + "<img src=\"https://ubisoft-avatars.akamaized.net/" + player.getUbisoft_id() + "/default_256_256.png\" width=\"50\" height=\"50\">" +
                "<br>****Операция Void Edge | EMEA**** <br>" +
                " Общая статистика <br>" +
                " **K-D:** " + general.getKd() + "<br>" +
                " **W-L:** " + general.getWl() + "<br>" +
                " **Матчей сыграно:** " + general.getGames_played());
        return builder.build().getDescription();
    }

    private String sendRankedMessage(R6Player player) {

        var ranked = player.getStats()[0].getQueue().getRanked();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setDescription("Статистика" + "<br>" +  "<img src=\"https://ubisoft-avatars.akamaized.net/" + player.getUbisoft_id() + "/default_256_256.png\" width=\"50\" height=\"50\">" +
                "<br>****Операция Void Edge | EMEA**** <br> "   +
                " Главное                                                       <br>" +
                "Текущий ранг: Gold 1, 3121 MMR         <br>" +
                " **Лучший ранг:** Gold 1, 3171 MMR  <br>" +
                " Дополнительно                                        <br>" +
                " **До повышения:** 79 MMR, 4 победы <br>" +
                " **Последний матч:** +24 MMR <br>" +
                " Статистика в рейт. игре <br>" +
                " **K-D:** " + ranked.getKd() + "<br>" +
                " **W-L:** " + ranked.getWl() + "<br>" +
                " **Матчей сыграно:** " + ranked.getGames_played());
        return builder.build().getDescription();
    }
}
