package controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-08-10 11:42 PM
 **/

public class ClientHandlerController extends Thread {

    private ArrayList<ClientHandlerController> clientList;
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;


    public ClientHandlerController(Socket localSocket, ArrayList<ClientHandlerController> clientList) {
        try {
            this.socket = localSocket;
            this.clientList = clientList;
            this.bufferedReader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
            this.printWriter = new PrintWriter(localSocket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {


        /*String msg;

        while (localSocket.isConnected()) {

            try {
                msg = bufferedReader.readLine();
                broadCastMessage(msg);

            } catch (IOException e) {
                closeEverything();
                break;
            }

        }*/

        try {
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                if (msg.equalsIgnoreCase( "exit")) {
                    break;
                }
                for (ClientHandlerController cl : clientList) {
                    cl.printWriter.write(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                bufferedReader.close();
                printWriter.close();
                localSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void closeEverything() {

        try {
            clientList.remove(this);
            broadCastMessage("SERVER > " + username + " has left the chat.");

            bufferedReader.close();
            printWriter.close();
//            bufferedWriter.close();
            localSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
