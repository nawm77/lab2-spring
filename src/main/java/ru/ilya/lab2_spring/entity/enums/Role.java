package ru.ilya.lab2_spring.entity.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Role {
    USER(1),
    ADMIN(10);

    final int code;

    Role(int code) {
        this.code = code;
    }

    public static Role fromCode(int code) {
        for (Role role : values()) {
            if (role.code == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
