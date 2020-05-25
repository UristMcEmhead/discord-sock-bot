package ru.itis.kpfu.bentos.discordbootbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Entity
@Profile({"dis","sock"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;
    @Column
    private String passwordHash;

    @OneToMany(mappedBy = "friendshipRequest", fetch = FetchType.EAGER)
    private List<User> friends;

    @OneToMany
    private List<FriendshipRequest> friendshipRequest;

}
