package com.example.team_sudoku_api.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTeam {
    @EmbeddedId
    private UserTeamId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    private LocalDateTime joinedAt;

    @OneToMany(mappedBy = "userTeam", cascade = CascadeType.PERSIST)
    private List<Log> logs = new ArrayList<>();

    @Embeddable
    @EqualsAndHashCode
    public static class UserTeamId implements Serializable {
        @Column(name = "user_id")
        private String userId;
        @Column(name = "team_id")
        private String teamId;

        public static UserTeamId create(String userId, String teamId) {
            UserTeamId id = new UserTeamId();
            id.userId = userId;
            id.teamId = teamId;
            return id;
        }
    }
}
