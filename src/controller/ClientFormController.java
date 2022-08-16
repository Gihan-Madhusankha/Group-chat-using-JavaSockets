package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author : Gihan Madhusankha
 * 2022-08-11 5:26 PM
 **/

public class ClientFormController extends Thread implements Initializable {
    public AnchorPane clientContext;
    public TextArea txtArea;
    public TextField txtMessage;
    public Label lblUsername;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    String username;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            txtArea.setEditable(false);
            username = LoginFormController.username;
            lblUsername.setText(username);

            socket = new Socket("localhost", 8624);
            System.out.println("Socket is connected with server!");
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendMessages(Socket socket) throws IOException {
        printWriter.write(username);
        printWriter.flush();
//        bufferedWriter.newLine();

        while (socket.isConnected()) {

//            Scanner scanner = new Scanner(System.in);

            String sendMsg = txtMessage.getText();
            printWriter.println(username + " : " + sendMsg);
            txtArea.appendText("ME : " + sendMsg + "\n");
            printWriter.flush();

        }

    }



    @Override
    public void run() {
        try {
            while (true) {
                String msg = bufferedReader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];

                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]);
                }

                if (cmd.equalsIgnoreCase(username + ":")) {
                    continue;
                } else if (fullMsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                txtArea.appendText(msg + "\n");
            }

            bufferedReader.close();
            printWriter.close();
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
