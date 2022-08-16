package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-08-10 11:35 PM
 **/

public class ServerFormController {

    public static final ArrayList<ClientHandlerController> clientList = new ArrayList<>();

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket = new ServerSocket(8624);

            while(!serverSocket.isClosed()) {
                System.out.println("Server is running...");
                socket = serverSocket.accept();
                System.out.println("client is Connected|");

                ClientHandlerController clientThread = new ClientHandlerController(socket, clientList);
                clientList.add(clientThread);
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
