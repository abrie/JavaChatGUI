package client;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

/**
 * This is the chat client program.
 * Type 'bye' to terminte the program.
 *
 * @author www.codejava.net
 */
public class ChatClient {
  private ConcurrentLinkedQueue<String> incoming;
  private BlockingQueue<String> outgoing;

  private String hostname;
  private int port;
  private String username;

  public ChatClient(String hostname, int port, String username) {
    this.hostname = hostname;
    this.port = port;
    this.username = username;

    this.incoming = new ConcurrentLinkedQueue<>();
    this.outgoing = new LinkedBlockingDeque<>();
  }

  public void execute() {
    try {
      Socket socket = new Socket(hostname, port);

      System.out.println("Connected to the chat server");

      new ReadThread(socket, this.incoming).start();
      new WriteThread(socket, this.outgoing).start();

      sendMessage(username); // Server expects first message to be the user's name;

    } catch (UnknownHostException ex) {
      System.out.println("Server not found: " + ex.getMessage());
    } catch (IOException ex) {
      System.out.println("I/O Error: " + ex.getMessage());
    } catch (InterruptedException ex) {
        System.out.println("Failed to send initial message" + ex.getMessage());
    }

  }

  public void sendMessage(String message) throws InterruptedException {
    outgoing.put(message);
  }

  public String pullMessage() {
    return incoming.poll();
  }
}
