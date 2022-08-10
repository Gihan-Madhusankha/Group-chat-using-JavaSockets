import java.io.*;
import java.net.Socket;

/**
 * @author : Gihan Madhusankha
 * 2022-08-10 11:42 PM
 **/

public class ClientHandlerController implements Runnable{
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String clientUsername;

    public ClientHandlerController(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.clientUsername = bufferedReader.readLine();
    }

    @Override
    public void run() {

    }
}
