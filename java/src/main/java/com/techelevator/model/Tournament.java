package com.techelevator.model;

public class Tournament {

    private int id;
    private int hostId;
    private String name;
    private double entry_fee;
    private String gameName;

    public Tournament(int id, int hostId, String name, double entry_fee, String gameName) {
        this.id = id;
        this.hostId = hostId;
        this.name = name;
        this.entry_fee = entry_fee;
        this.gameName = gameName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(double entry_fee) {
        this.entry_fee = entry_fee;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
