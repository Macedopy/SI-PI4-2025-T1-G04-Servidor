//Responsável de User: Bruno Macedo

package clarifica.obra;

import java.io.IOException;

import clarifica.infrastructure.Constants; 
import clarifica.infrastructure.server.ManipulationServer; 

public class App 
{
    public static void main(String[] args) {
        ManipulationServer server = new ManipulationServer(); 

        try {
            server.start(Constants.hostId);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                server.stop(); 
            } catch (IOException closeE) {
                closeE.printStackTrace();
            }
        }
    }
}
