package com.example.team_sudoku_api.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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

    public void setTeam(Team team){
        this.team=team;
    }

    public void setUser(User user){
        this.user=user;
    }

    public static UserTeam create(String uid,String teamId){
        UserTeam userTeam = new UserTeam();
        userTeam.id=UserTeamId.create(uid,teamId);
        return userTeam;
    }

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
