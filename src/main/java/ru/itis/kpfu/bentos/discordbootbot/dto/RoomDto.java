package ru.itis.kpfu.bentos.discordbootbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import ru.itis.kpfu.bentos.discordbootbot.model.Room;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Profile({"dis","sock"})
public class RoomDto {

    private List<UserDto> users;
    private List<MessageDto> messages;
    private Long id;

    public RoomDto(Integer id){
        this.id = Long.parseLong(id.toString());
    }

    public static RoomDto from(Room room) {
        return RoomDto.builder()
                .messages(RoomDto.from(room).getMessages())
                .users(RoomDto.from(room).getUsers())
                .build();
    }

}
