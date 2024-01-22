package com.example.a2023aparty;

public class eventInfo {
    private int id;
    private String event, time, locations, date;

    public eventInfo(int id, String event, String time, String locations, String date) {
        this.id = id;
        this.event = event;
        this.time = time;
        this.locations = locations;
        this.date = date;
    }

    public eventInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
