import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.util.ArrayList;

/**
 * @author Gregory Elias
 * @version 1.0.0
 * */
public class DisplayGrid {
    public static ArrayList<StackPane> gridArray = new ArrayList<>();

    /**
     * @return Returns a 5x6 blank gridPane.
     * */
    public static GridPane createGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                StackPane temp = createBox();
                gridPane.add(temp, j, i);
                gridArray.add(temp);
            }
        }
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    /**
     * @return Returns a stackPane for each square in Jordle.
     * */
    public static StackPane createBox() {
        StackPane stackPane = new StackPane();
        Rectangle temp = new Rectangle(12, 12);
        temp.setWidth(60);
        temp.setHeight(60);
        temp.setFill(Color.WHITE);
        Label text = new Label();
        text.setFont(new Font(30));
        text.setLabelFor(temp);
        stackPane.getChildren().addAll(temp, text);
        return stackPane;
    }

    /**
     * @param stackPane Inputted stackPane.
     * @param text Changes the label node of the inputted stackPane to this parameter.
     * */
    public static void setText(StackPane stackPane, String text) {
        ObservableList<Node> children = stackPane.getChildren();
        for (Node node : children) {
            if (node instanceof Label) {
                ((Label) node).setText(text);
            }
        }
    }
}