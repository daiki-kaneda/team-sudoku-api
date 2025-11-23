package com.example.team_sudoku_api.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class UserRole {
    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "uid")
    @MapsId("userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    @MapsId("roleId")
    private Role role;
    
    @Embeddable
    @Data
    public static class UserRoleId implements Serializable{
        private String userId;
        private Long roleId;

        public static UserRoleId create(String userId,Long roleId){
            UserRoleId id = new UserRoleId();
            id.setUserId(userId);
            id.setRoleId(roleId);
            return id;
        }
    }
}
