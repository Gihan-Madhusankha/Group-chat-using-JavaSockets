import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : Gihan Madhusankha
 * 2022-08-11 5:26 PM
 **/

public class ClientFormController {
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String username;

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

            Socket socket = new Socket("localhost", 8624);

            ClientFormController clientFormController = new ClientFormController(socket, username);
            clientFormController.listenMessages(socket);
            clientFormController.sendMessages(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendMessages(Socket socket) throws IOException {
        bufferedWriter.write(username);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        while (socket.isConnected()) {

            Scanner scanner = new Scanner(System.in);

            String sendMsg = scanner.nextLine();
            bufferedWriter.write(sendMsg);
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }
        System.out.println("send()");

    }

    private void listenMessages(Socket socket) {

        new Thread(() -> {

            String listenMsg;

            while (socket.isConnected()) {

                try {
                    listenMsg = bufferedReader.readLine();
                    System.out.println(listenMsg);

                } catch (IOException e) {
                    System.out.println("catch of listen");
                    e.printStackTrace();
                }

            }

            System.out.println("listen()");

        }).start();

    }

}
