package clarifica.infrastructure.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException; // Import added for UnknownHostException

import clarifica.infrastructure.Constants;

public class ManipulationClient implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    @Override
    public void run() {
        System.out.println("Client running...");
        try {
            startConnection("127.0.0.1", Constants.hostId);
            System.out.println("Server said: " + sendMessage("hello server"));
            stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        if (out != null) out.close();
        if (clientSocket != null) clientSocket.close();
    }
}