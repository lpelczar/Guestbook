package com.codecool.guestbook;

public class Note {

    public String name;
    public String message;

    public Note(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
