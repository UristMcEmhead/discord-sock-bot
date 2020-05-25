package ru.itis.kpfu.bentos.discordbootbot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.events.MessageReceived;

import java.awt.*;

@Component
@Profile("dis")
public class TestCommand extends Command {

    @Override
    public void execute(GenericEvent event) {
        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;

        MessageChannel channel = messageReceivedEvent.getChannel();
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
                " **K-D:** 1.35 (1155 / 854) \n" +
                " **W-L:** 1.38 (116 / 84) \n" +
                " **Матчей сыграно:**200");
        builder.setThumbnail("https://tabstats.com/images/r6/ranks/?rank=16&champ=");
        builder.setAuthor("UristMcEmhead", null, "https://ubisoft-avatars.akamaized.net/a9326d59-8ceb-4d73-9be6-d56606eb51ab/default_256_256.png");
        builder.setFooter("EmbedBuilder is the best");
        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public Headers header() {
        return Headers.test;
    }

    @Override
    public String description() {
        return "This command created for temporal tests, Doesn't call it please)";
    }
}
