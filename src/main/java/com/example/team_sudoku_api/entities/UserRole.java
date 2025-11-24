package com.example.team_sudoku_api.entities;

import java.io.Serializable;

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

    public static UserRole create(UserRoleId id,User user,Role role){
        UserRole userRole = new UserRole();
        userRole.id=id;
        userRole.user=user;
        userRole.role=role;
        return userRole;
    }
    
    @Embeddable
    @EqualsAndHashCode
    public static class UserRoleId implements Serializable{
        private String userId;
        private Long roleId;

        public static UserRoleId create(String userId,Long roleId){
            UserRoleId id = new UserRoleId();
            id.userId = userId;
            id.roleId = roleId;
            return id;
        }
    }
}
