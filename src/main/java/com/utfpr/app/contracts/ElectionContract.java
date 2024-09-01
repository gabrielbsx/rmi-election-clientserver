package com.utfpr.app.contracts;

public interface ElectionContract {

    void vote(Integer candidateNumber);

    String countVotes();

    String showResults();

    String showWinner();

    void addCandidate(Integer number, String name);

    Boolean isCandidateExists(Integer number);
}
