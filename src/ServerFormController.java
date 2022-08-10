import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author : Gihan Madhusankha
 * 2022-08-10 11:35 PM
 **/

public class ServerFormController {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server is running...");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
