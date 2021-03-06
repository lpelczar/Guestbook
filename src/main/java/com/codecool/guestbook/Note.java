package com.codecool.guestbook;

import java.io.Serializable;

public class Note implements Serializable {

    private String name;
    private String message;
    private String date;

    Note(String name, String message, String date) {
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }
}
