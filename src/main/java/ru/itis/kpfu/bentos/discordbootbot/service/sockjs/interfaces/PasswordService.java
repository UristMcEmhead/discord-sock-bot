package ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces;

public interface PasswordService {

    String createPasswordHash(String password);

    boolean compare(String password, String passwordHash);
}
