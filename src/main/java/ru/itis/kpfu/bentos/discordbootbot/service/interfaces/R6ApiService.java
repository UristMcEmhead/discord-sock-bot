package ru.itis.kpfu.bentos.discordbootbot.service.interfaces;

import com.fasterxml.jackson.databind.JsonNode;

public interface R6ApiService {

    String getUser(String username, String platform);

}
