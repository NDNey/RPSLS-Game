package com.rpsls;

public class Player {

    // Fields
    private final int id;
    private final String name;
    private int PlayerScore = 0;


    //constructors

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //Business methods
    public void win() {
        PlayerScore++;
    }

    //Accessor methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return PlayerScore;
    }

    public Choice randomChoice() {
        Choice choice = null;
        int random = (int) Math.floor(Math.random() * 5);
        choice = Choice.values()[random];

        return choice;
    }

    public String toString() {
        return "Player: id=" + getId() + ", name=" + getName() + ", wins=" + getWins();
    }


}