package ru.itis.kpfu.bentos.discordbootbot.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.bentos.discordbootbot.model.User;

import java.util.List;
import java.util.Optional;

@Repository
@Profile({"dis","sock"})
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByName(String name);

}
