package ru.itis.kpfu.bentos.discordbootbot.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.bentos.discordbootbot.model.Message;

import java.util.List;

@Repository
@Profile({"dis","sock"})
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByRoomId(Long id);

}
