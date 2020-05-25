package ru.itis.kpfu.bentos.discordbootbot.service.sockjs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.discordbootbot.dto.UserDto;
import ru.itis.kpfu.bentos.discordbootbot.dto.forms.SignInForm;
import ru.itis.kpfu.bentos.discordbootbot.dto.forms.SignUpForm;
import ru.itis.kpfu.bentos.discordbootbot.model.User;
import ru.itis.kpfu.bentos.discordbootbot.repository.UserRepository;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.PasswordService;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.UserService;

@Service
@AllArgsConstructor
@Profile("sock")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    @Override
    public UserDto signIn(SignInForm signInForm) {

        var userCandidate = userRepository.getUserByName(signInForm.getName());
        if (userCandidate.isPresent()) {
            var user = userCandidate.get();
            if (passwordService.compare(signInForm.getPassword(), user.getPasswordHash())) {
                return UserDto.from(user);
            } else return null;
        } else return null;
    }

    @Override
    public UserDto signUp(SignUpForm signUpForm) {

        var passwordHash = passwordService.createPasswordHash(signUpForm.getPassword());

        var userCandidate = User.builder()
                .name(signUpForm.getName())
                .passwordHash(passwordHash)
                .build();

        var user = userRepository.save(userCandidate);

        return UserDto.from(user);
    }
}
