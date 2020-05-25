package ru.itis.kpfu.bentos.discordbootbot.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.kpfu.bentos.discordbootbot.dto.forms.SignInForm;
import ru.itis.kpfu.bentos.discordbootbot.dto.forms.SignUpForm;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.CookieService;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
@Profile("sock")
public class UserController {

    private final UserService userService;
    private final CookieService cookieService;

    @GetMapping("/signUp")
    public String getSignUp() {
        return "sign_up";
    }

    @GetMapping("/signIn")
    public String getSignIn() {
        return "sign_in";
    }

    @PostMapping("/signIn")
    public String signIn(SignInForm form, HttpServletResponse response) {
        var user = userService.signIn(form);
        var cookie = new Cookie("from", user.getName());
        if (userService.signIn(form) != null) {
            response.addCookie(cookieService.configure(cookie));
            var auth = new Cookie("X-Authorization", "token");
            auth.setPath("/");
            auth.setMaxAge(500000);
            response.addCookie(auth);
            return "redirect:/room/0";
        } else return "redirect:/signIn?error";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpForm form) {
        userService.signUp(form);

        return "redirect:/room/0";
    }

}
