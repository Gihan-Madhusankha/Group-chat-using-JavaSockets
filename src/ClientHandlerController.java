import java.net.Socket;

/**
 * @author : Gihan Madhusankha
 * 2022-08-10 11:42 PM
 **/

public class ClientHandlerController {
    Socket socket;

    public ClientHandlerController(Socket socket) {
        this.socket = socket;
    }
}
