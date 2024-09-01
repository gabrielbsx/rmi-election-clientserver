package com.utfpr.app.services;

import java.util.ArrayList;

import com.utfpr.app.contracts.ElectionContract;
import com.utfpr.app.entity.Candidate;

public class ElectionServiceImpl implements ElectionContract {

    public static ArrayList<Candidate> candidates;

    public ElectionServiceImpl() {
        if (ElectionServiceImpl.candidates == null) {
            ElectionServiceImpl.candidates = new ArrayList<>();
        }
    }

    @Override
    public void vote(Integer candidateNumber) {
        for (Candidate candidate : ElectionServiceImpl.candidates) {
            if (candidate.getNumber().equals(candidateNumber)) {
                candidate.addVote();

                System.out.println("Voto computado para: " + candidate.getName());
                System.out.println("Total de votos: " + candidate.getVotes());

                candidates.set(candidates.indexOf(candidate), candidate);
            }
        }

    }

    @Override
    public String countVotes() {
        String votes = "";

        for (Candidate candidate : ElectionServiceImpl.candidates) {
            votes += "Candidato: " + candidate.getName() + " - Votos: " + candidate.getVotes() + "\n";
        }

        return votes;
    }

    @Override
    public String showResults() {
        String results = "";

        for (Candidate candidate : ElectionServiceImpl.candidates) {
            results += "Candidato: " + candidate.getName() + " - Votos: " + candidate.getVotes() + "\n";
        }

        return results;
    }

    @Override
    public String showWinner() {
        Candidate winner = ElectionServiceImpl.candidates.get(0);
        Candidate draw = null;

        for (Candidate candidate : ElectionServiceImpl.candidates) {
            if (candidate.getVotes() > winner.getVotes()) {
                winner = candidate;
            }
        }

        for (Candidate candidate : ElectionServiceImpl.candidates) {
            if (candidate.getVotes().equals(winner.getVotes()) && !candidate.getName().equals(winner.getName())) {
                draw = candidate;
            }
        }

        if (draw != null) {
            return "Houve um empate entre " + winner.getName() + " e " + draw.getName() + " com " + winner.getVotes()
                    + " votos.";
        }

        return "O vencedor é: " + winner.getName() + " com " + winner.getVotes() + " votos.";
    }

    @Override
    public void addCandidate(Integer number, String name) {
        ElectionServiceImpl.candidates.add(new Candidate(number, name));
    }

    @Override
    public Boolean isCandidateExists(Integer number) {
        for (Candidate candidate : ElectionServiceImpl.candidates) {
            if (candidate.getNumber().equals(number)) {
                return true;
            }
        }

        return false;
    }
}
