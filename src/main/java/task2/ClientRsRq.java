package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientRsRq {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try (Socket clientSocket = new Socket("netology.homework", ServerRqRs.PORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            writer.println("Client: " + clientSocket.getInetAddress() + " is connected to server");

            System.out.println("Message from server: " + reader.readLine());

            String username = input.nextLine();
            writer.println(username);

            System.out.println("Message from server: " + reader.readLine());

            String isChild = input.nextLine();
            writer.println(isChild);

            System.out.println("Message from server: " + reader.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
