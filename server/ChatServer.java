package server;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This is the chat server program.
 * Press Ctrl + C to terminate the program.
 *
 * original author: www.codejava.net
 * converted to support a GUI by github.com/abrie
 */
public class ChatServer {
  private int port;
  private Set<String> userNames = new HashSet<>();
  private Set<UserThread> userThreads = new HashSet<>();

  public ChatServer(int port) {
    this.port = port;
  }

  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(port)) {

      System.out.println("Chat Server is listening on port " + port);

      while (true) {
        Socket socket = serverSocket.accept();

        UserThread newUser = new UserThread(socket, this);
        userThreads.add(newUser);
        newUser.start();
      }

    } catch (IOException ex) {
      System.out.println("Error in the server: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Syntax: java ChatServer <port-number>");
      System.exit(0);
    }

    int port = Integer.parseInt(args[0]);

    ChatServer server = new ChatServer(port);
    server.execute();
  }

  /**
   * Broadcasts a message to all users
   */
  void broadcast(String message) {
    for (UserThread aUser : userThreads) {
      aUser.sendMessage(message);
    }
  }

  /**
   * Stores a username of newly connected client.
   * NB/TODO: Enforce uniqueness.
   */
  void addUserName(String userName) {
    userNames.add(userName);
    System.out.println("New user connected:" + userName);
  }

  /**
   * Remove a username and broadcast news of of their departure.
   */
  void removeUser(String userName, UserThread aUser) {
    boolean removed = userNames.remove(userName);
    if (removed) {
      userThreads.remove(aUser);
      broadcast("The user " + userName + " quit.");
      System.out.println("User disconnected:" + userName);
    }
  }

  Set<String> getUserNames() {
    return this.userNames;
  }

  /**
   * Returns true if there are other users connected.
   */
  boolean hasUsers() {
    return !this.userNames.isEmpty();
  }
}
