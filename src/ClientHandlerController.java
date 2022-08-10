import java.io.*;
import java.net.Socket;

/**
 * @author : Gihan Madhusankha
 * 2022-08-10 11:42 PM
 **/

public class ClientHandlerController implements Runnable{
    Socket socket;
    BufferedReader bufferedReader;

    public ClientHandlerController(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {

    }
}
