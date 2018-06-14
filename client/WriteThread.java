package client;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 *
 * @author www.codejava.net
 */
public class WriteThread extends Thread {
  private PrintWriter writer;
  private Socket socket;
  private BlockingQueue<String> outgoing;

  public WriteThread(Socket socket, BlockingQueue<String> outgoing) {
    this.socket = socket;
    this.outgoing = outgoing;

    try {
      OutputStream output = socket.getOutputStream();
      writer = new PrintWriter(output, true);
    } catch (IOException ex) {
      System.out.println("Error getting output stream: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  public void run() {
    String text;

    do {
      try {
        text = outgoing.take();
        writer.println(text);
      } catch (InterruptedException ex) {
        //Thread.currentThread().interrupt();
        System.out.println("outgoing queue read is interrupted." + ex.getMessage());
        break;
      }
    } while (!text.equals("bye"));

    try {
      socket.close();
    } catch (IOException ex) {

      System.out.println("Error writing to server: " + ex.getMessage());
    }
  }
}
