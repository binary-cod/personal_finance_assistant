package networking;

import domain.Expense;
import domain.Income;
import services.ExpenseService;
import services.IncomeService;
import ui.UI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PFAServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {

            Integer numberOfConnections = 0;

            System.out.println("Server is up and running...");

            while (true) {
                Socket incoming = serverSocket.accept();
                numberOfConnections++;

                System.out.println(numberOfConnections+ " Connection accepted, creating handler for it; ");

                ClientHandler clientHandler = new ClientHandler(incoming);

                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
