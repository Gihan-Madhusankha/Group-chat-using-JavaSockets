import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : Gihan Madhusankha
 * 2022-08-10 11:35 PM
 **/

public class ServerFormController {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server is running...");


            while (!serverSocket.isClosed()) {
                Socket localSocket = serverSocket.accept();
                System.out.println("Client accepted...");

                ClientHandlerController clientHandlerController = new ClientHandlerController(localSocket);
                new Thread(clientHandlerController).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
