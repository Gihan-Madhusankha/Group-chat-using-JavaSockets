package controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-08-10 11:42 PM
 **/

public class ClientHandlerController implements Runnable {
    private static final ArrayList<ClientHandlerController> clientList = new ArrayList<>();
    Socket localSocket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String username;

    public ClientHandlerController(Socket localSocket) {
        try {
            this.localSocket = localSocket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(localSocket.getOutputStream()));
            this.username = bufferedReader.readLine();
            clientList.add(this);
            broadCastMessage("SERVER : " + username + " joined to group chat.");

        } catch (IOException e) {
            closeEverything();
        }
    }

    private void broadCastMessage(String messageToAllClients) throws IOException {

        for (ClientHandlerController clientHandlerController : clientList) {
            if (!clientHandlerController.username.equals(username)) {
                clientHandlerController.bufferedWriter.write(messageToAllClients);
                clientHandlerController.bufferedWriter.newLine();
                clientHandlerController.bufferedWriter.flush();
            }
        }

    }

    @Override
    public void run() {

        String msg;

        while (localSocket.isConnected()) {

            try {
                msg = bufferedReader.readLine();
                broadCastMessage(msg);

            } catch (IOException e) {
                closeEverything();
                break;
            }

        }

    }


    public void closeEverything() {

        try {
            clientList.remove(this);
            broadCastMessage("SERVER > " + username + " has left the chat.");

            bufferedReader.close();
            bufferedWriter.close();
            localSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
