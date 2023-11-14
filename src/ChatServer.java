package Level3;
/* 
 --> Task: Implement a Chat Application
Develop a chat application using Java and
networking libraries like Socket or Java NIO.
This task will enhance their knowledge of
network programming, client-server
architecture, and message exchange.*/

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatServer {
    private static final int PORT = 12346;
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Chat Server started. Listening on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
        	serverSocket.setReuseAddress(true);

        	while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                Scanner reader = new Scanner(socket.getInputStream());
                writer = new PrintWriter(socket.getOutputStream(), true);
                clientWriters.add(writer);

                while (true) {
                    String message = reader.nextLine();
                    if (message == null) {
                        return;
                    }
                    System.out.println("Received: " + message);
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } 
//            finally {
//                clientWriters.remove(writer);
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
  //          }
        }
    }

    private static void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }
}
