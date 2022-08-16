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

        try {
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                for (ClientHandlerController cl : clientList) {
                    cl.printWriter.println(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
