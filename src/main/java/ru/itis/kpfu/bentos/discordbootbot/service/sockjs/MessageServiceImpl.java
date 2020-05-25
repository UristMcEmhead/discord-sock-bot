package ru.itis.kpfu.bentos.discordbootbot.service.sockjs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;
import ru.itis.kpfu.bentos.discordbootbot.model.Room;
import ru.itis.kpfu.bentos.discordbootbot.repository.MessageRepository;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.MessageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("sock")
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public List<MessageDto> getAllByRoom(Room room) {
        return null;
    }

    @Override
    public List<MessageDto> getAllByRoomId(Long roomId) {
        return messageRepository.findAllByRoomId(roomId)
                .stream()
                .map(MessageDto::from)
                .collect(Collectors.toList());
    }

}
