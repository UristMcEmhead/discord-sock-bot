//package ru.itis.kpfu.bentos.discordbootbot.utils;
//
//
//import org.springframework.stereotype.Component;
//import ru.itis.kpfu.bentos.discordbootbot.dto.UserDto;
//import ru.itis.kpfu.bentos.discordbootbot.repository.UserRepository;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class LoginLogoutHandler extends SimpleUrlAuthenticationSuccessHandler implements LogoutHandler {
//
//    private final UserRepository userRepository;
//
//    public LoginLogoutHandler( UserRepository userRepository) {
//        this.userRepository = userRepository;
//        super.setUseReferer(true);
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        var session = request.getSession();
//        var details = (UserDetails) authentication.getPrincipal();
//
//        var userCandidate = userRepository.getUserByName(details.getUsername());
//
//        var user = userCandidate.get();
//
//        System.out.println("setting session for " + user.getName());
//        session.setAttribute("user", user);
//        session.setAttribute("me", UserDto.from(user));
//
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//
//    @Override
//    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        var session = request.getSession();
//        System.out.println("cleaning session for " + session.getAttribute("me"));
//
//        session.setAttribute("user", null);
//        session.setAttribute("me", null);
//    }
//}
