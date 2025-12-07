//Responsável de User: Bruno Macedo e Bruno Martins

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

    public static void main(String[] args) {
        ManipulationClient cliente = new ManipulationClient();
        cliente.run(); 
    }
     // novo método estático para obter token
    public static String obterTokenDoServidor() {
        ManipulationClient client = new ManipulationClient();
        String tokenRecebido = "";
        try {
            client.startConnection("127.0.0.1", Constants.hostId);
            
            tokenRecebido = client.sendMessage("GERAR_TOKEN");
            
            client.stopConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERRO_TOKEN"; 
        }
        return tokenRecebido;
    }

    @Override
    public void run() {
        System.out.println("Client running...");
        try {
            startConnection("127.0.0.1", Constants.hostId); //

            String nomeDaObra = "Teste_01"; 
            String resposta = sendMessage("CRIAR_OBRA " + nomeDaObra);

            System.out.println("O Servidor respondeu: " + resposta);

            if (resposta.startsWith("ID_OBRA:")) {
                String idRecebido = resposta.split(":")[1]; 
                System.out.println(">>> Sucesso! O ID da nova obra é: " + idRecebido);
            }

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