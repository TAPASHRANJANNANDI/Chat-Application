package Level3;
/*
Task: Implement a Chat Application
Develop a chat application using Java and
networking libraries like Socket or Java NIO.
This task will enhance their knowledge of
network programming, client-server
architecture, and message exchange.*/

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12346);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner consoleInput = new Scanner(System.in);

            System.out.println("Connected to the Chat Server.");

            while (true) {
                System.out.print("You: ");
                String message = consoleInput.nextLine();
                writer.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
