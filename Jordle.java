import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.Node;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.io.File;

/**
 * @author Gregory Elias
 * @version 1.0.0
 * */
public class Jordle extends Application {
    public static int min;
    public static int max = 5;
    public static boolean finish;
    public TableView<Gamer> table = new TableView<>();
    public static boolean isWon;
    public static String name;
    public static Stage primary;
    public static Stage winWindow;

    /**
     * @param args Main method.
     * */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage Sets up primary window.
     * */
    public void start(Stage primaryStage) {
        RandomWord.setRandomWord();
        primary = primaryStage;

        Label bottomLabel = new Label("Try guessing a word!");

        Text title = new Text("Jordle");

        StackPane sP = new StackPane();
        sP.getChildren().add(title);

        Button restart = new Button("Restart");
        Button instructions = new Button("Instructions");

        HBox hBox = new HBox(10);
        hBox.getChildren().add(bottomLabel);
        hBox.getChildren().add(restart);
        hBox.getChildren().add(instructions);

        Label instructionsLabel = new Label();

        Stage newWindow = new Stage();
        StackPane secondaryLayout = new StackPane();

        Scene secondScene = new Scene(secondaryLayout, 350, 350);
        secondaryLayout.getChildren().add(instructionsLabel);
        newWindow.setScene(secondScene);

        Label error = new Label("Error! Enter a valid word.");
        Image errorI;
        try {
            errorI = new Image(new FileInputStream("error.png"));

        } catch (FileNotFoundException e) {
            errorI = new Image("error.png");
        }
        ImageView errorImage = new ImageView(errorI);
        Format.errorImage(errorImage);
        Button okay = new Button("Ok");
        Stage errorWindow = new Stage();
        okay.setOnAction(event -> errorWindow.close());
        HBox errorLayout = new HBox();
        errorLayout.getChildren().addAll(errorImage, error, okay);
        Scene thirdScene = new Scene(errorLayout, 350, 150);
        errorWindow.setScene(thirdScene);

        instructions.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                newWindow.setX(primaryStage.getX() - (secondScene.getWidth() - primaryStage.getWidth()) / 2);
                newWindow.setY(primaryStage.getY() - (secondScene.getHeight() - primaryStage.getHeight()) / 2);
                newWindow.show();
                if (!newWindow.isFocused()) {
                    newWindow.requestFocus();
                }
            }
        });

        GridPane gridPane = DisplayGrid.createGrid();
        restart.setOnAction((event) -> restart(event, bottomLabel, gridPane));
        Label tableName = new Label("Leaderboard");
        CheckBox checkBox = new CheckBox("Enable Dictionary");
        checkBox.setOnAction((event -> ValidWords.isDictionary = checkBox.isSelected()));
        LeaderBoard.setTable(table);
        ArrayList<Gamer> gamerArrayList = LeaderBoard.updateScore(null);
        for (Gamer gamer : gamerArrayList) {
            table.getItems().add(gamer);
        }
        VBox left = new VBox(tableName, table, checkBox);

        Format.leftFormat(left);
        Format.tableName(tableName);
        Format.errorLayout(errorLayout);
        Format.errorWindow(errorWindow);
        Format.setFocusTraversable(okay);
        Format.newWindow(newWindow);
        Format.instructions(instructionsLabel);
        Format.bottomLayer(hBox);
        Format.setFocusTraversable(instructions);
        Format.setFocusTraversable(restart);
        Format.primaryStage(primaryStage);
        Format.bottomLabel(bottomLabel);
        Format.checkBox(checkBox);
        Format.title(title);
        Format.topLayer(sP);

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(sP, Pos.CENTER);
        BorderPane.setAlignment(left, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(left, new Insets(12, 12, 12, 12));
        Scene scene = new Scene(root, 650, 600);
        scene.setOnKeyPressed(keyEvent -> keyPressed(keyEvent, primaryStage, errorWindow, thirdScene, bottomLabel));
        root.setTop(sP);
        root.setBottom(hBox);
        root.setCenter(gridPane);
        root.setLeft(left);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(windowEvent -> {
            try {
                if (finish) {
                    table.getItems().clear();
                    nullNameEvent();
                }
                errorWindow.close();
                newWindow.close();
                winWindow.close();
                primaryStage.close();
            } catch (NullPointerException e) {
                primaryStage.close();
            }
        });
    }

    /**
     * Event that occurs when the game is won.
     * */
    public void winPrompt() {
        winWindow = new Stage();
        Text won = new Text("You won!");
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        Format.wonText(won);

        TextField inputTextField = new TextField();
        inputTextField.setPromptText("Enter your name!");
        inputTextField.setFocusTraversable(false);

        Scene scene = new Scene(layout, 200, 150);
        Button yes = new Button("Confirm");
        Button no = new Button("Cancel");

        layout.setTop(won);
        layout.setCenter(inputTextField);
        HBox hBox = new HBox(yes, no);
        Format.confirmOrDeny(hBox);
        layout.setBottom(hBox);
        winWindow.setScene(scene);
        Format.wonWindow(winWindow);

        BorderPane.setAlignment(won, Pos.CENTER);
        BorderPane.setAlignment(inputTextField, Pos.CENTER);
        BorderPane.setMargin(inputTextField, new Insets(12, 12, 12, 12));

        yes.setOnAction(event -> {
            winWindow.close();
            table.getItems().clear();
            if (inputTextField.getText() != null && inputTextField.getText().length() != 0) {
                name = inputTextField.getText();
                ArrayList<Gamer> temp = LeaderBoard.updateScore(new Gamer(
                        name, min / 5));
                for (Gamer game : temp) {
                    table.getItems().add(game);
                }
                name = null;
            } else {
                name = null;
                nullNameEvent();
            }
        });
        no.setOnAction(event -> {
            winWindow.close();
            table.getItems().clear();
            name = null;
            nullNameEvent();
        });

        winWindow.setX(primary.getX() - 10 - (scene.getWidth() - primary.getWidth()) / 2);
        winWindow.setY(primary.getY() - 10 - (scene.getHeight() - primary.getHeight()) / 2);
        winWindow.show();
        if (!winWindow.isFocused()) {
            winWindow.requestFocus();
        }
        winWindow.setOnCloseRequest(windowEvent -> {
            table.getItems().clear();
            nullNameEvent();
        });
    }

    /**
     * Event that occurs when no input for name.
     * */
    public void nullNameEvent() {
        try {
            String computerName = InetAddress.getLocalHost().getHostName();
            ArrayList<Gamer> temp = LeaderBoard.updateScore(new Gamer(computerName, min / 5));
            for (Gamer game : temp) {
                table.getItems().add(game);
            }
        } catch (UnknownHostException e) {
            String computerName = "Unknown";
            ArrayList<Gamer> temp = LeaderBoard.updateScore(new Gamer(computerName, min / 5));
            for (Gamer game : temp) {
                table.getItems().add(game);
            }
        } finally {
            if (winWindow.isShowing()) {
                winWindow.close();
            }
        }
    }

    /**
     * @param ignoredEvent Unused event.
     * @param bottomLabel Label that appears at the bottom of the screen.
     * @param gridPane (Resets) the gridPane.
     * */
    public void restart(ActionEvent ignoredEvent, Label bottomLabel, GridPane gridPane) {
        isWon = false;
        finish = false;
        Position.position = 0;
        min = 0;
        max = 5;
        RandomWord.setRandomWord();
        bottomLabel.setText("Try guessing a word!");
        ObservableList<Node> node = gridPane.getChildren();
        for (Node stack : node) {
            if (stack instanceof StackPane) {
                ObservableList<Node> nodes = ((StackPane) stack).getChildren();
                for (Node n : nodes) {
                    if (n instanceof Rectangle) {
                        ((Rectangle) n).setFill(Color.WHITE);
                    } else if (n instanceof Label) {
                        ((Label) n).setText("");
                    }
                }
            }
        }
    }

    /**
     * @param keyEvent The data collected from the key pressed.
     * @param primaryStage The primary stage.
     * @param bottomLabel Text that appears at bottom of screen.
     * @param errorWindow Separate window for invalid input
     * @param thirdScene Separate scene for invalid input.
     * */
    public void keyPressed(KeyEvent keyEvent, Stage primaryStage,
                           Stage errorWindow, Scene thirdScene, Label bottomLabel) {
        if (keyEvent.getCode().isLetterKey() && !finish) {
            if (Position.position < max) {
                Position.setPosition(Position.position + 1);
            }
            (new MediaPlayer(new Media(new File("click.mp3").toURI().toString()))).play();
            DisplayGrid.setText(DisplayGrid.gridArray.get(Position.position - 1), keyEvent.getCode().toString());
        } else if (keyEvent.getCode() == KeyCode.ENTER && !finish) {
            ArrayList<StackPane> paneArrayList = new ArrayList<>();
            for (int i = min; i < max; i++) {
                paneArrayList.add(DisplayGrid.gridArray.get(i));
            }
            ArrayList<String> colors = WordCheck.getWord(paneArrayList);
            if (colors == null || colors.size() == 0) {
                errorWindow.setX(primaryStage.getX() - (thirdScene.getWidth() - primaryStage.getWidth()) / 2);
                errorWindow.setY(primaryStage.getY() - (thirdScene.getHeight() - primaryStage.getHeight()) / 2);
                (new MediaPlayer(new Media(new File("error.mp3").toURI().toString()))).play();
                errorWindow.show();
                if (!errorWindow.isFocused()) {
                    errorWindow.requestFocus();
                }
            } else {
                for (String s : colors) {
                    if (!s.equals("GREEN")) {
                        finish = false;
                        break;
                    }
                    finish = true;
                }
                for (int i = 0; i < 5; i++) {
                    ObservableList<Node> nodes = paneArrayList.get(i).getChildren();
                    for (Node node : nodes) {
                        if (node instanceof Rectangle) {
                            if (colors.get(0).equals("GREEN")) {
                                ((Rectangle) node).setFill(Color.GREEN);
                            } else if (colors.get(0).equals("YELLOW")) {
                                ((Rectangle) node).setFill(Color.YELLOW);
                            } else {
                                ((Rectangle) node).setFill(Color.GRAY);
                            }
                            colors.remove(0);
                        }
                    }
                }
                if (finish) {
                    (new MediaPlayer(new Media(new File("finish.mp3").toURI().toString()))).play();
                    bottomLabel.setText("Congratulations! You guessed the word.");
                    winPrompt();
                    isWon = true;
                } else if (max > 25) {
                    finish = true;
                    bottomLabel.setText("Game over! The word was " + RandomWord.word + ".");
                    (new MediaPlayer(new Media(new File("gameOver.mp3").toURI().toString()))).play();
                }
                max += 5;
                min += 5;
                (new MediaPlayer(new Media(new File("enter.mp3").toURI().toString()))).play();
            }

        } else if (keyEvent.getCode() == KeyCode.BACK_SPACE && !finish) {
            if (Position.position > min) {
                DisplayGrid.setText(DisplayGrid.gridArray.get(Position.position - 1), "");
                Position.setPosition(Position.position - 1);
            }
            (new MediaPlayer(new Media(new File("undo.mp3").toURI().toString()))).play();
        }
    }
}
