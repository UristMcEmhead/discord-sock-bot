package ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces;

import ru.itis.kpfu.bentos.discordbootbot.dto.UserDto;
import ru.itis.kpfu.bentos.discordbootbot.dto.forms.SignInForm;
import ru.itis.kpfu.bentos.discordbootbot.dto.forms.SignUpForm;

public interface UserService {


    UserDto signIn(SignInForm signInForm);
    UserDto signUp(SignUpForm signUpForm);

}
