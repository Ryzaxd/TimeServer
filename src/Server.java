import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        final int PORT = 1978;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening for connections...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Connection from " + clientSocket.getInetAddress());

                    Scanner input = new Scanner(clientSocket.getInputStream());
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                    String request = input.nextLine();
                    if ("q".equals(request)) {
                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        output.println(currentTime);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
