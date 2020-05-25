package ru.itis.kpfu.bentos.discordbootbot.service;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.discordbootbot.service.interfaces.EmojiService;

import java.util.ArrayList;
import java.util.Random;

@Service
@Profile("dis")
public class EmojiServiceImpl implements EmojiService {

    @Override
    public Emoji getRandomEmoji() {
        var emojis = (ArrayList<Emoji>) EmojiManager.getAll();
        var random = new Random();
        var emojiIndex = random.nextInt(emojis.size());
        return emojis.get(emojiIndex);
    }

    @Override
    public String getEmojiUnicode(Emoji emoji) {
        return emoji.getUnicode();
    }
}
