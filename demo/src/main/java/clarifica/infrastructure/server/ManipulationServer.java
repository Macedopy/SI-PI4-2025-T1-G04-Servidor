package clarifica.infrastructure.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ManipulationServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port + ". Waiting for client...");
        
        while (true) { 
            clientSocket = serverSocket.accept(); 
            System.out.println("Client connected from: " + clientSocket.getInetAddress().getHostAddress());

            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);  
                    String outputResponse;

                    if ("hello server".equalsIgnoreCase(inputLine.trim())) {
                        outputResponse = "hello client";
                    } 
                    else if ("GERAR_TOKEN".equalsIgnoreCase(inputLine.trim())) {
                        int numeroAleatorio = (int) (Math.random() * 100000);
                        
                        String tokenPersonalizado = "Clarifica" + numeroAleatorio;
                        
                        System.out.println("Processando geração de token: " + tokenPersonalizado);
                        
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
                System.out.println("I/O error with client: " + e.getMessage());
            } finally {
                if (clientSocket != null) {
                    clientSocket.close();
                    System.out.println("Client disconnected.");
                }
            }
        }
    }

    public void stop() throws IOException {
        if (in != null) in.close();
        if (out != null) out.close();
        if (serverSocket != null) serverSocket.close();
        if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
    }
}