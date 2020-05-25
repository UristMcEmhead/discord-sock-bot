package ru.itis.kpfu.bentos.discordbootbot.command;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.discordbootbot.dto.MessageDto;
import ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces.FriendService;

@Component
@Profile("sock")
@AllArgsConstructor
public class ApproveCommand extends SockCommand {

    private final FriendService friendService;

    @Override
    public String execute(MessageDto message) {

        var from = message.getFrom().getName();
        var to = message.getText().split(" ")[1];
        var approve = message.getText().split(" ")[2];
        if (approve.equals(true)) {
            friendService.approve(to, from, true);
            return "Now " + from + "and " + to + " are friends";
        } else {
            return "User " + to + "doesn't approve friendship request";
        }

    }

    @Override
    public Headers header() {
        return Headers.approve;
    }

    @Override
    public String description() {
        return "Call to approve friendship some user";
    }
}
