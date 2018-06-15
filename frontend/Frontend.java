package frontend;

import client.ChatClient;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Frontend extends Application {
  private ChatClient client;
  private TextArea textArea;
  private TextField textField;

  private TextField hostField;
  private TextField portField;
  private TextField nameField;

  private Scene chatScene;
  private Scene settingsScene;

  private AnimationTimer timer;

  public static void main(String[] args) {
    launch(args);
  }

  private String getTextFieldContents(TextField field) {
    String text = field.getText();
    if ((text != null && !text.isEmpty())) {
      return text;
    } else {
      return null;
    }
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setOnCloseRequest(event -> {
      if (client != null) {
        client.close();
      }

      if (timer != null) {
        timer.stop();
      }
    });

    primaryStage.setTitle("SocketChat");
    Button btn = new Button();
    btn.setText("Send");
    btn.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        try {
          String text = getTextFieldContents(textField);
          if (text != null) {
            client.sendMessage(text);
          }
        } catch (InterruptedException ex) {
          System.out.println("Failed to send message" + ex.getMessage());
        }
      }
    });

    timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        String message = client.pullMessage();
        if (message != null) {
          textArea.appendText("\n"+message);
        }

        String command = client.pullCommand();
        if (command != null) {
          if (command.equals("close")) {
            stop();
            client.close();
            primaryStage.setScene(settingsScene);
          }
        }
      }
    };

    textArea = new TextArea();
    textArea.setEditable(false);
    textArea.setWrapText(true);

    textField = new TextField();

    GridPane gridPane = new GridPane();
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setHgrow(Priority.ALWAYS);
    gridPane.getColumnConstraints().addAll(column1);

    RowConstraints row1 = new RowConstraints();
    row1.setVgrow(Priority.ALWAYS);
    gridPane.getRowConstraints().addAll(row1);

    gridPane.setConstraints(textArea, 0, 0, 2, 1);
    gridPane.setConstraints(textField, 0, 1, 1, 1);
    gridPane.setConstraints(btn, 1, 1, 1, 1);
    gridPane.getChildren().addAll(textArea, textField, btn);


    Button connectButton = new Button();
    connectButton.setText("Connect");
    connectButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        String hosttext = getTextFieldContents(hostField);
        String porttext = getTextFieldContents(portField);
        String nametext = getTextFieldContents(nameField);

        int port = Integer.parseInt(porttext);

        client = new ChatClient(hosttext, port, nametext);
        boolean success = client.execute();

        if (success) {
          textArea.setText("");
          textField.setText("");
          primaryStage.setScene(chatScene);
          timer.start();
        } else {
          System.out.println("Failed to connect.");
          System.out.println(client.getErrorMessage());
        }
      }
    });

    Label hostLabel = new Label("Host name");
    hostField = new TextField();
    hostField.setText("localhost");

    Label portLabel = new Label("Host port");
    portField = new TextField();
    portField.setText("9999");

    Label nameLabel = new Label("Username");
    nameField = new TextField();
    nameField.setText("abrie");

    GridPane settingsPane = new GridPane();
    settingsPane.setConstraints(hostLabel, 0, 0);
    settingsPane.setConstraints(hostField, 1, 0);
    settingsPane.setConstraints(portLabel, 0, 1);
    settingsPane.setConstraints(portField, 1, 1);
    settingsPane.setConstraints(nameLabel, 0, 2);
    settingsPane.setConstraints(nameField, 1, 2);
    settingsPane.setConstraints(connectButton, 1, 3);
    settingsPane.getChildren().addAll(connectButton, hostLabel, hostField, portLabel, portField, nameLabel, nameField);

    chatScene = new Scene(gridPane, 300, 250);
    settingsScene = new Scene(settingsPane, 300, 250);

    primaryStage.setScene(settingsScene);
    primaryStage.show();
  }
}
