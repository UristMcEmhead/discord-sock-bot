package ru.itis.kpfu.bentos.discordbootbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import ru.itis.kpfu.bentos.discordbootbot.model.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Profile({"dis","sock"})
public class MessageDto {

    private String text;
    private UserDto from;
    private RoomDto room;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .text(message.getText())
                .from(UserDto.from(message.getFrom()))
                .build();
    }
}
