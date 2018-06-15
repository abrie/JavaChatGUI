package client;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 *
 * original author: www.codejava.net
 * converted to support a GUI by github.com/abrie
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

    for(;;) {
      try {
        text = outgoing.take();
        if (text.equals("TERMINATE")) {
          break;
        }
        writer.println(text);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        System.out.println("Outgoing queue read is interrupted." + ex.getMessage());
        break;
      }
    };
  }
}
