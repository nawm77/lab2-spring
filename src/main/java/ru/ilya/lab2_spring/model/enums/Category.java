package ru.ilya.lab2_spring.model.enums;

import lombok.Getter;

@Getter
public enum Category {
    CAR(1),
    BUS(2),
    TRUCK(3),
    MOTORCYCLE(4);

    final int code;

    Category(int code) {
        this.code = code;
    }

    public static Category fromCode(int code) {
        for (Category category : values()) {
            if (category.code == code) {
                return category;
            }
        }
        throw new IllegalArgumentException("No such category with code: " + code);
    }
}
