package ru.itis.kpfu.bentos.discordbootbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import ru.itis.kpfu.bentos.discordbootbot.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Profile({"dis","sock"})
public class UserDto {

    private String name;

    public static UserDto from(User user) {
        return UserDto.builder()
                .name(user.getName())
                .build();
    }

}
