package utb.fai;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        int max_threads = Integer.parseInt(args[1]);

        ExecutorService executor = Executors.newFixedThreadPool(max_threads);;
        Socket clientSocket;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                clientSocket = serverSocket.accept();
                executor.execute(new ClientThread(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
