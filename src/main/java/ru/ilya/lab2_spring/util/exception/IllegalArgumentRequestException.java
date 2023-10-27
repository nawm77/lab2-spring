package ru.ilya.lab2_spring.util.exception;

import java.util.List;

public class IllegalArgumentRequestException extends Exception{
    public IllegalArgumentRequestException(String message) {
        super(message);
    }
    public IllegalArgumentRequestException (List<String> messages){
        super("Validation errors: " + String.join("\n ", messages));
    }
}
