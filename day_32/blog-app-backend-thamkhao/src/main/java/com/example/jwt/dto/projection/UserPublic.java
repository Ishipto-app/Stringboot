package com.example.jwt.dto;

import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

public interface UserPublic {
    Integer getId();

    String getName();

    String getEmail();

    String getAvatar();

    List<Role> getRoles();

    @RequiredArgsConstructor
    class UserPublicImpl implements UserPublic {
        private final User user;

        @Override
        public Integer getId() {
            return this.user.getId();
        }

        @Override
        public String getName() {
            return this.user.getName();
        }

        @Override
        public String getEmail() {
            return this.user.getEmail();
        }

        @Override
        public String getAvatar() {
            return this.user.getAvatar();
        }

        @Override
        public List<Role> getRoles() {
            return this.user.getRoles();
        }
    }

    static UserPublic of(User user) {
        return new UserPublicImpl(user);
    }
}
