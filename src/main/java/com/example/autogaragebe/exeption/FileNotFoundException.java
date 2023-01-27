package com.example.autogaragebe.exeption;

public class FileNotFoundException extends RuntimeException {

    private String message;

    public FileNotFoundException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

}
