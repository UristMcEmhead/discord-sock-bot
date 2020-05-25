package ru.itis.kpfu.bentos.discordbootbot.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Profile("sock")
public class SignInForm {

    private String name;
    private String password;

}
