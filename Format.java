import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontSmoothingType;
import javafx.stage.Stage;

/**
 * @author Gregory Elias
 * @version 1.0.0
 * */
public class Format {

    /**
     * @param label Label to format.
     * */
    public static void instructions(Label label) {
        label.setText("Welcome to Jordle! "
                + "At the start of each game, there will be a randomly generated 5 letter word you have to guess. "
                + "Once you type in a guess and press enter, there will be three colors that can show up per letter. "
                + "If it shows green, that means that letter is in that exact position relative to the word. "
                + "If it shows yellow, that means that letter is in the word. "
                + "If it shows grey, that means that letter is not in the word. ");
        label.setFont(Font.font("Lucida Sans Unicode", 15));
        label.setWrapText(true);
        label.setMinWidth(50);
        label.setMaxWidth(300);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        label.setTextFill(Color.CORAL);
    }

    /**
     * @param bottomLabel Label to format.
     * */
    public static void bottomLabel(Label bottomLabel) {
        bottomLabel.setFont(new Font(15));
    }

    /**
     * @param title Text to format.
     * */
    public static void title(Text title) {
        title.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        title.setStyle("-fx-font-size: 60px");
        title.setFill(Color.BLANCHEDALMOND);
    }

    /**
     * @param button Button to format.
     * */
    public static void setFocusTraversable(Button button) {
        button.setFocusTraversable(false);
    }

    /**
     * @param hBox HBox to format.
     * */
    public static void bottomLayer(HBox hBox) {
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(20));
        hBox.setStyle("-fx-background-color: GRAY");
    }

    /**
     * @param imageView ImageView to format.
     * */
    public static void errorImage(ImageView imageView) {
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setSmooth(true);
    }

    /**
     * @param sP StackPane to format.
     * */
    public static void topLayer(StackPane sP) {
        sP.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, new CornerRadii(0), Insets.EMPTY)));
    }

    /**
     * @param newWindow Stage to format.
     * */
    public static void newWindow(Stage newWindow) {
        newWindow.setTitle("Instructions");
        fixedScreen(newWindow, 350, 350);
    }

    /**
     * @param errorWindow Stage to format.
     * */
    public static void errorWindow(Stage errorWindow) {
        errorWindow.setTitle("Error");
        fixedScreen(errorWindow, 350, 150);
    }

    /**
     * @param errorLayout HBox to format.
     * */
    public static void errorLayout(HBox errorLayout) {
        errorLayout.setAlignment(Pos.CENTER);
        errorLayout.setSpacing(20);
        errorLayout.setPadding(new Insets(20));
    }

    /**
     * @param tableName Label to format.
     * */
    public static void tableName(Label tableName) {
        tableName.setFont(Font.font(null, FontWeight.BOLD, 20));
        tableName.setAlignment(Pos.CENTER);
    }

    /**
     * @param checkBox CheckBox to format.
     * */
    public static void checkBox(CheckBox checkBox) {
        checkBox.setStyle(
                "-fx-border-color: lightblue; "
                        + "-fx-font-size: 15;"
                        + "-fx-border-width: 2;"
                        + "-fx-border-radius: 5;"
                        + "-fx-border-insets: -5; "
        );
        checkBox.setFocusTraversable(false);
        checkBox.setLineSpacing(15);
    }

    /**
     * @param left VBox to format.
     * */
    public static void leftFormat(VBox left) {
        left.setSpacing(5);
        left.setAlignment(Pos.CENTER);
    }

    /**
     * @param primaryStage Stage to format.
     * */
    public static void primaryStage(Stage primaryStage) {
        primaryStage.setTitle("Jordle");
        fixedScreen(primaryStage, 650, 600);
    }

    /**
     * @param won Text to format.
     * */
    public static void wonText(Text won) {
        won.setTextOrigin(VPos.CENTER);
        won.setTextAlignment(TextAlignment.JUSTIFY);
        won.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        won.setFontSmoothingType(FontSmoothingType.LCD);
        won.setFill(Color.GREEN);
    }

    /**
     * @param hBox HBox to format.
     * */
    public static void confirmOrDeny(HBox hBox) {
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
    }

    /**
     * @param newWindow Stage to format.
     * */
    public static void wonWindow(Stage newWindow) {
        fixedScreen(newWindow, 250, 200);
    }

    /**
     * @param window Stage to format.
     * @param height Gives room of 50px.
     * @param width Gives room of 50px.
     * */
    public static void fixedScreen(Stage window, int width, int height) {
        window.setMinWidth(width);
        window.setMaxWidth(width + 50);
        window.setMinHeight(height);
        window.setMaxHeight(height + 50);
    }
}