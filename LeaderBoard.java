import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Gregory Elias
 * @version 1.0.0
 * */
public class LeaderBoard {
    static ArrayList<Gamer> gamerArrayList = new ArrayList<>();

    /**
     * @param table Formats table.
     * */
    public static void setTable(TableView<Gamer> table) {
        TableColumn<Gamer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<Gamer, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        table.setFocusTraversable(false);
        table.getColumns().add(nameColumn);
        table.getColumns().add(scoreColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Play to add your score!"));



        for (Gamer gamer : gamerArrayList) {
            table.getItems().add(gamer);
        }
    }

    /**
     * @param gamer Adds gamer to gamerArrayList.
     * @return Returns updated gamerArrayList.
     * */
    public static ArrayList<Gamer> updateScore(Gamer gamer) {
        try {
            gamerArrayList = new ArrayList<>();
            FileReader fileReader = new FileReader("leaderboard.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(",");
                gamerArrayList.add(new Gamer(temp[0], Integer.parseInt(temp[1])));
            }
            if (gamer != null) {
                gamerArrayList.add(gamer);
            }
            Collections.sort(gamerArrayList);
            FileWriter fileWriter = new FileWriter("leaderboard.txt");
            for (Gamer player : gamerArrayList) {
                fileWriter.append(player.toString());
                fileWriter.append("\n");
            }
            fileWriter.close();
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gamerArrayList;
    }
}