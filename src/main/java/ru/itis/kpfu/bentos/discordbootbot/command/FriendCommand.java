package ru.itis.kpfu.bentos.discordbootbot.command;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.FriendService;

@Component
@Profile("sock")
@AllArgsConstructor
public class FriendCommand extends SockCommand {

    private final FriendService friendService;

    @Override
    public String execute(MessageDto message) {

        var from = message.getFrom().getName();
        var to = message.getText().split(" ")[1];
        friendService.makeFriend(to, from);
        return from + "send friendship request to " + to;
    }

    @Override
    public Headers header() {
        return Headers.friend;
    }

    @Override
    public String description() {
        return "Write to become a friend with a specific user";
    }
}
