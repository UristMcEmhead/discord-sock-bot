package ru.itis.kpfu.bentos.discordbootbot.service.interfaces;

import com.vdurmont.emoji.Emoji;

public interface EmojiService {

    Emoji getRandomEmoji();

    String getEmojiUnicode(Emoji emoji);

}
