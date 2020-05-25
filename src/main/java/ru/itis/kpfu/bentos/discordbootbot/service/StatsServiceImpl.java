package ru.itis.kpfu.bentos.discordbootbot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.itis.kpfu.bentos.discordbootbot.dto.R6Player;
import ru.itis.kpfu.bentos.discordbootbot.service.interfaces.R6ApiService;
import ru.itis.kpfu.bentos.discordbootbot.service.interfaces.StatsService;

import java.awt.*;

@Service
@AllArgsConstructor
@Profile("dis")
public class StatsServiceImpl implements StatsService {

    private final R6ApiService r6ApiService;
    private final ObjectMapper objectMapper;

    @Override
    public void getPlayerStats(MessageReceivedEvent event) {

        var message = event.getMessage().getContentRaw().split(" ");
        var username = message[1];
        var platform = message[2];
        var type = message[3];

        try {
            var user = r6ApiService.getUser(username, platform);
            R6Player player = objectMapper.readValue(user, R6Player.class);

            MessageChannel channel = event.getChannel();
            if (type.equals("gen"))
                sendGeneralMessage(player, channel);
            else if (type.equals("ran")) sendRankedMessage(player, channel); else channel.sendMessage("Illegal request paramater found at position 3").queue();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (HttpClientErrorException e){
            event.getChannel().sendMessage("Can't find user with this name").queue();
        }

    }

    private void sendGeneralMessage(R6Player player, MessageChannel channel) {

        var general = player.getStats()[0].getGeneral();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Статистика");
        builder.setColor(new Color(0xA0A0A0));
        builder.setDescription(" ****Операция Void Edge | EMEA**** \n" +
                " Общая статистика \n" +
                " **K-D:** " + general.getKd() + "\n" +
                " **W-L:** " + general.getWl() + "\n" +
                " **Матчей сыграно:** " + general.getGames_played());
        builder.setThumbnail("https://steamuserimages-a.akamaihd.net/ugc/960855957564094579/EA03D8EBDEEC2D684476AD81A0B372CB74144311/");
        builder.setAuthor(player.getUsername(), null, "https://ubisoft-avatars.akamaized.net/" + player.getUbisoft_id() + "/default_256_256.png");
        builder.setFooter("Using r6stats api (в будущем изменю, так как у текущей не актуальная документация)");
        channel.sendMessage(builder.build()).queue();
    }

    private void sendRankedMessage(R6Player player, MessageChannel channel) {

        var ranked = player.getStats()[0].getQueue().getRanked();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Статистика");
        builder.setColor(new Color(0xA0A0A0));
        builder.setDescription(" ****Операция Void Edge | EMEA**** \n" +
                " Главное                                                       \n" +
                " Текущий ранг: Gold 1, 3121 MMR         \n" +
                " **Лучший ранг:** Gold 1, 3171 MMR  \n" +
                " Дополнительно                                        \n" +
                " **До повышения:** 79 MMR, 4 победы \n" +
                " **Последний матч:** +24 MMR \n" +
                " Статистика в рейт. игре \n" +
                " **K-D:** " + ranked.getKd() + "\n" +
                " **W-L:** " + ranked.getWl() + "\n" +
                " **Матчей сыграно:** " + ranked.getGames_played());
        builder.setThumbnail("https://tabstats.com/images/r6/ranks/?rank=16&champ=");
        builder.setAuthor(player.getUsername(), null, "https://ubisoft-avatars.akamaized.net/" + player.getUbisoft_id() + "/default_256_256.png");
        builder.setFooter("Using r6stats api (в будущем изменю, так как у текущей не актуальная документация)");
        channel.sendMessage(builder.build()).queue();
    }
}
