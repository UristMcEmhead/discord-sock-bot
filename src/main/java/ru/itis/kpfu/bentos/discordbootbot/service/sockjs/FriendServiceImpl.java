package ru.itis.kpfu.bentos.discordbootbot.service.sockjs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.discordbootbot.model.FriendshipRequest;
import ru.itis.kpfu.bentos.discordbootbot.repository.FriendshipRepository;
import ru.itis.kpfu.bentos.discordbootbot.repository.MessageRepository;
import ru.itis.kpfu.bentos.discordbootbot.repository.UserRepository;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.FriendService;

@Service
@Profile("sock")
@AllArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;
    private final MessageRepository messageRepository;

    @Override
    public void makeFriend(String name, String from) {
        friendshipRepository.save(FriendshipRequest.builder()
                .isApproved(false)
                .to(userRepository.getUserByName(name).get())
                .build());
    }

    @Override
    public boolean isApproved(String name, String from) {
        return userRepository.getUserByName(from).get()
                .getFriendshipRequest()
                .stream()
                .anyMatch(user -> user
                        .getTo()
                        .getName()
                        .equals(name));
    }

    @Override
    public void approve(String name, String from, boolean approve) {
        friendshipRepository.setIsApproved(userRepository.getUserByName(name).get().getId(), approve);
    }
}
