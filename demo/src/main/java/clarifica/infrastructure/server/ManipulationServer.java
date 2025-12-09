// Responsável de Server: Bruno Macedo e Bruno Martins
package clarifica.infrastructure.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ManipulationServer {
    private ServerSocket serverSocket;
    //Permitindo múltiplas conexões de clientes
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port + ". Waiting for client...");
        
        while (true) { 
            //aceitando a conexão
            Socket clientSocket = serverSocket.accept(); 
            System.out.println("Client connected from: " + clientSocket.getInetAddress().getHostAddress());

            //criando o funcionário (que contém todo aquele código antigo do try-catch)
            ClientHandler handler = new ClientHandler(clientSocket);
            
            new Thread(handler).start();
        }
    }

    public void stop() throws IOException {
        if (serverSocket != null) serverSocket.close();
    }
}