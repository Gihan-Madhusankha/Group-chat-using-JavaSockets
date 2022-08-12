import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : Gihan Madhusankha
 * 2022-08-11 5:26 PM
 **/

public class ClientFormController {
    private final Socket socket;
    private final String username;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;

    public ClientFormController(Socket socket, String username) throws IOException {
        this.socket = socket;
        this.username = username;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username > ");
            String username = scanner.nextLine();

            Socket socket = new Socket("localhost", 8000);

            ClientFormController client = new ClientFormController(socket, username);
            client.sendMessages(socket);
            client.listenMessages(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void listenMessages(Socket socket) {

        new Thread(() -> {

            String listenMsg;

            while (socket.isConnected()) {

                try {
                    listenMsg = bufferedReader.readLine();
                    System.out.println(listenMsg);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }).start();

    }

    private void sendMessages(Socket socket) throws IOException {
        bufferedWriter.write(username);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        Scanner scanner = new Scanner(System.in);

        while (socket.isConnected()) {
            String sendMsg = scanner.nextLine();
            bufferedWriter.write(username + " : " + sendMsg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

    }

}
