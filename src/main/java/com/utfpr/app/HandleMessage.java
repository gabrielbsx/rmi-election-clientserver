package com.utfpr.app;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.utfpr.app.services.ElectionServiceImpl;

public class HandleMessage {

    public Socket socket;

    public HandleMessage(Socket socket) {
        this.socket = socket;
    }

    public void handle(String message) {
        try {
            OutputStream output = this.socket.getOutputStream();

            String outputMessage = "";

            switch (message.toLowerCase().split(" ")[0]) {
                case "addcandidato":
                    System.out.println("Adicionando candidato\n");

                    Integer number;
                    String name;

                    try {
                        String numberStr = message.split(" ")[1];
                        String[] nameArray = message.split(" ");

                        number = Integer.valueOf(numberStr);

                        nameArray[0] = "";
                        nameArray[1] = "";

                        name = String.join(" ", nameArray).trim();
                    } catch (NumberFormatException ex) {
                        outputMessage = outputMessage + "Erro ao adicionar candidato\n";
                        break;
                    }

                    System.out.println("Número: " + number);
                    System.out.println("Nome: " + name);

                    ElectionServiceImpl election = new ElectionServiceImpl();

                    if (election.isCandidateExists(number)) {
                        outputMessage += "Candidato já existe\n\n";

                        break;
                    }

                    election.addCandidate(number, name);

                    outputMessage += "Candidato adicionado\n\n";
                    break;

                case "addvoto":
                    System.out.println("Adicionando voto\n");

                    Integer candidateNumber;

                    try {
                        candidateNumber = Integer.valueOf(message.split(" ")[1]);
                    } catch (NumberFormatException ex) {
                        outputMessage = outputMessage + "Erro ao adicionar voto\n";
                        break;
                    }

                    System.out.println("Número do candidato: " + candidateNumber);

                    ElectionServiceImpl electionService = new ElectionServiceImpl();

                    if (!electionService.isCandidateExists(candidateNumber)) {
                        outputMessage += "Candidato não existe\n\n";

                        break;
                    }

                    electionService.vote(candidateNumber);

                    outputMessage += "Voto adicionado\n\n";
                    break;

                case "resultados":
                    System.out.println("Mostrando resultados\n");

                    ElectionServiceImpl electionServiceResult = new ElectionServiceImpl();

                    outputMessage += "Resultados: " + electionServiceResult.showResults() + "\n\n";
                    outputMessage += "\n\n";
                    break;

                case "vencedor":
                    System.out.println("Mostrando vencedor\n");

                    ElectionServiceImpl electionServiceWinner = new ElectionServiceImpl();

                    outputMessage += electionServiceWinner.showWinner() + "\n\n";

                    outputMessage += "\n\n";
                    break;

                case "votos":
                    System.out.println("Mostrando votos\n");

                    ElectionServiceImpl electionServiceVotes = new ElectionServiceImpl();

                    outputMessage += "Votos: " + electionServiceVotes.countVotes() + "\n\n";

                    outputMessage += "\n\n";
                    break;

                default:
                    System.out.println("Comando desconhecido: " + message);

                    outputMessage += "Comando desconhecido\n\n";
                    break;
            }

            outputMessage += "Comandos disponiveis:\n";
            outputMessage += "addcandidato \t- Adiciona um candidato\t\taddcandidato {numero} {nome}\n";
            outputMessage += "addvoto \t- Adiciona um voto\t\taddvoto {numero do candidato}\n";
            outputMessage += "exit \t- Fecha a conexão\n";
            outputMessage += "resultados \t- Mostra os resultados\n";
            outputMessage += "vencedor \t- Mostra o vencedor\n";
            outputMessage += "votos \t- Mostra os votos\n";
            outputMessage += "\n\nComando: ";

            output.write(outputMessage.getBytes());

            output.flush();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
