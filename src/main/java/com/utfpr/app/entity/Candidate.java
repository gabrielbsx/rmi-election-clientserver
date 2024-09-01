package com.utfpr.app.entity;

public class Candidate {

    Integer number;
    String name;
    Integer votes;

    public Candidate(Integer number, String name) {
        this.number = number;
        this.name = name;
        this.votes = 0;
    }

    public void addVote() {
        this.votes += 1;
    }

    public Integer getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public Integer getVotes() {
        return this.votes;
    }
}
