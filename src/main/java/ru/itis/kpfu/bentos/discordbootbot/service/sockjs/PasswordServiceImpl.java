package ru.itis.kpfu.bentos.discordbootbot.service.sockjs;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Profile;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.PasswordService;

@Service
@Profile("sock")
public class PasswordServiceImpl implements PasswordService {

    public String createPasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean compare(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }

}

