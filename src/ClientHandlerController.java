import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-08-10 11:42 PM
 **/

public class ClientHandlerController implements Runnable{
    private static final ArrayList<ClientHandlerController> clientHandlerList = new ArrayList<>();
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String clientUsername;

    public ClientHandlerController(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.clientUsername = bufferedReader.readLine();
        clientHandlerList.add(this);
    }

    @Override
    public void run() {

    }
}
