package client;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 * This thread is responsible for reading server's input and printing it
 * to the console.
 * It runs in an infinite loop until the client disconnects from the server.
 *
 * @author www.codejava.net
 */
public class ReadThread extends Thread {
  private BufferedReader reader;
  private Socket socket;
  private ConcurrentLinkedQueue<String> incoming;

  public ReadThread(Socket socket, ConcurrentLinkedQueue<String> incoming) {
    this.socket = socket;
    this.incoming = incoming;

    try {
      InputStream input = socket.getInputStream();
      reader = new BufferedReader(new InputStreamReader(input));
    } catch (IOException ex) {
      System.out.println("Error getting input stream: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  public void run() {
    while (true) {
      try {
        String response = reader.readLine();
        if (response != null) {
          incoming.offer(response);
        } else {
          incoming.offer("You have disconnected.");
          break;
        }
      } catch (IOException ex) {
        System.out.println("Error reading from server: " + ex.getMessage());
        ex.printStackTrace();
        break;
      }
    }
  }
}
