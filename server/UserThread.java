package server;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This thread encapsulates a client connection.
 *
 * original author: www.codejava.net
 * converted to support a GUI by github.com/abrie
 */
public class UserThread extends Thread {
  private Socket socket;
  private ChatServer server;
  private PrintWriter writer;

  public UserThread(Socket socket, ChatServer server) {
    this.socket = socket;
    this.server = server;
  }

  public void run() {
    String userName = "";

    try {
      InputStream input = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      OutputStream output = socket.getOutputStream();
      writer = new PrintWriter(output, true);

      printUsers();

      userName = reader.readLine();
      server.addUserName(userName);

      String serverMessage = "New user connected: " + userName;
      server.broadcast(serverMessage);

      String clientMessage;

      for(;;) {
        clientMessage = reader.readLine();
        if (clientMessage.equals("quit")) {
          break;
        }
        serverMessage = "[" + userName + "]: " + clientMessage;
        server.broadcast(serverMessage);
      }

      server.removeUser(userName, this);
      socket.close();

    } catch (Exception ex) {
      server.removeUser(userName, this);
      System.out.println("UserThread exception:" + ex.getMessage());
    }
  }

  /**
   * Sends a list of online users to the newly connected user.
   */
  void printUsers() {
    if (server.hasUsers()) {
      writer.println("Connected users: " + server.getUserNames());
    } else {
      writer.println("No other users connected");
    }
  }

  /**
   * Send a message to this client.
   */
  void sendMessage(String message) {
    writer.println(message);
  }
}
