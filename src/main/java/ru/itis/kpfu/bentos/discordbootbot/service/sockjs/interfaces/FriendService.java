package ru.itis.kpfu.bentos.discordbootbot.service.sockjs.interfaces;

public interface FriendService {

    void makeFriend(String name, String from);
    boolean isApproved(String name, String from);
    void approve(String name, String from, boolean approve);

}
