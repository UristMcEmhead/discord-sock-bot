package ru.itis.kpfu.bentos.discordbootbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.itis.kpfu.bentos.discordbootbot.dto.Wrapper;
import ru.itis.kpfu.bentos.discordbootbot.service.interfaces.R6ApiService;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
@AllArgsConstructor
@Profile({"dis","sock"})
public class R6ApiServiceImpl implements R6ApiService {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public String getUser(String username, String platform) throws HttpClientErrorException {

        RestTemplate restTemplate = new RestTemplate();

        var temp = restTemplate.getForObject("https://r6stats.com/api/player-search/" + username + "/" + platform, String.class);
        var wrapper = objectMapper.readValue(temp, Wrapper[].class);

        return restTemplate.getForObject("https://r6stats.com/api/stats/" + wrapper[0].getUbisoft_id(), String.class);
    }
}
