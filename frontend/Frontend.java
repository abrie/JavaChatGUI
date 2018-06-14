package frontend;

import client.ChatClient;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Frontend extends Application {
  static private ChatClient client;
  private TextArea textArea;

  public static void main(String[] args) {

    String hostname = "localhost";
    int port = 9999;

    client = new ChatClient(hostname, port);
    client.execute();

    launch(args);
  }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              try {
                client.sendMessage("Hello world!");
              } catch (InterruptedException ex) {
                System.out.println("Failed to send message" + ex.getMessage());
              }
            }
        });

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setText("Hi");

        StackPane root = new StackPane();
        root.getChildren().add(textArea);
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

        try {
          client.sendMessage("abrie"); // Server expects first message to be the user's name;
        } catch (InterruptedException ex) {
          System.out.println("Failed to send initial message" + ex.getMessage());
        }

        AnimationTimer timer = new AnimationTimer() {

          @Override
          public void handle(long now) {
            String message = client.pullMessage();
            if (message != null) {
              System.out.println("Received message: `" + message + "`");
              textArea.appendText("\n"+message);
            }
          }

        };

        timer.start();
    }
}
