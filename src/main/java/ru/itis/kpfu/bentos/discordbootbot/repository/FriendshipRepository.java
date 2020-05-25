package ru.itis.kpfu.bentos.discordbootbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.kpfu.bentos.discordbootbot.model.FriendshipRequest;

public interface FriendshipRepository extends JpaRepository<FriendshipRequest, Long> {

    @Modifying
    @Query("update FriendshipRequest request set request.isApproved =:isApproved where request.to.id=:id")
    void setIsApproved(@Param("id") Long id, @Param("isApproved") boolean isApproved);

}
