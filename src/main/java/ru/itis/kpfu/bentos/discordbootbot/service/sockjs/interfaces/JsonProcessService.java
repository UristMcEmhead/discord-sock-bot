package ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces;

import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;

public interface JsonProcessService {

    String parse(MessageDto messageDto);
    MessageDto parse(String json);


}
