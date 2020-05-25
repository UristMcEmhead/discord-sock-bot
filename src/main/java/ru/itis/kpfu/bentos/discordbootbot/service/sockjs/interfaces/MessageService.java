package ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces;


import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;
import ru.itis.kpfu.bentos.discordbootbot.model.Room;

import java.util.List;

public interface MessageService {

    List<MessageDto> getAllByRoom(Room room);
    List<MessageDto> getAllByRoomId(Long roomId);

}
