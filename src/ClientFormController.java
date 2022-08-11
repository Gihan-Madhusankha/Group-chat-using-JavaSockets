import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : Gihan Madhusankha
 * 2022-08-11 5:26 PM
 **/

public class ClientFormController {

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username > ");
            String username = scanner.nextLine();

            Socket socket = new Socket("localhost", 8000);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
