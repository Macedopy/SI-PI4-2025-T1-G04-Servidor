package clarifica.infrastructure.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Implementa Runnable para rodar em paralelo (Thread)
public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Thread " + Thread.currentThread().getId() + " recebeu: " + inputLine);
                String outputResponse;

                if ("hello server".equalsIgnoreCase(inputLine.trim())) {
                    outputResponse = "hello client";
                } 
                else if ("GERAR_TOKEN".equalsIgnoreCase(inputLine.trim())) {
                    int numeroAleatorio = (int) (Math.random() * 100000);
                    String tokenPersonalizado = "Clarifica" + numeroAleatorio;
                    System.out.println("Gerando token na Thread: " + tokenPersonalizado);
                    outputResponse = tokenPersonalizado;
                }
                else if ("bye".equalsIgnoreCase(inputLine.trim())) {
                    outputResponse = "bye";
                    out.println(outputResponse);
                    break;
                } else {
                    outputResponse = "unrecognised greeting or command";
                }
                out.println(outputResponse);
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com cliente: " + e.getMessage());
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}