package ru.itis.kpfu.bentos.discordbootbot.service.sockjs;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.CookieService;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Service
@Profile("sock")
public class CookieServiceImpl implements CookieService {

    public Optional<Cookie> findCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    public Cookie configure(Cookie cookie){
        cookie.setPath("/");
        cookie.setMaxAge(630720000);
        return cookie;
    }
}
