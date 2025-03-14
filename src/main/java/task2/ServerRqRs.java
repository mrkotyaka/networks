package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRqRs {
    public static final Integer PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("ServerRqRs started. Listening on port " + serverSocket.getLocalPort());

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    String clientMessages = bufferedReader.readLine();
                    System.out.printf("New connection accepted. Info: %s, port: %d%n", clientMessages, clientSocket.getPort());

                    System.out.println("Запрос имени...");
                    printWriter.println("Welcome to netology.network! Write your name: ");
                    String userName = bufferedReader.readLine();
                    System.out.println("Client response: " + userName);

                    System.out.println("Возрастное ограничение...");
                    printWriter.println("Are you child? (yes/no)");
                    String isChild = bufferedReader.readLine();
                    System.out.println("Client response: " + isChild);

                    System.out.println("Приглашение в актуальную зону...");
                    switch (isChild.toLowerCase()) {
                        case "yes" -> {
                            String zone = "Welcome to the kids area, " + userName + "! Let's play!";
                            printWriter.println(zone);
                            System.out.println("Sending to client: " + zone);
                        }
                        case "no" -> {
                            String zone = "Welcome to the adult zone, " + userName + "! Have a good rest, or a good working day!";
                            printWriter.println(zone);
                            System.out.println("Sending to client: " + zone);
                        }
                        default -> printWriter.println("Wrong age. Try again later.");
                    }
                }
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}