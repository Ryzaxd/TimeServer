import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 1978;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             Scanner scanner = new Scanner(System.in);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             Scanner input = new Scanner(socket.getInputStream())) {

            while (true) {
                System.out.print("Press 'q' to get the time and date, or 'exit' to quit: ");
                String message = scanner.nextLine();
                output.println(message);

                if ("q".equals(message)) {
                    String response = input.nextLine();
                    System.out.println("Server time and date: " + response);
                } else if ("exit".equals(message)) {
                    System.out.println("Exiting.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
